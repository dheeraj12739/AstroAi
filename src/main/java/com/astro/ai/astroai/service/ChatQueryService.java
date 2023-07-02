package com.astro.ai.astroai.service;

import com.astro.ai.astroai.model.AstroAppPlanetAndDashaDTO;
import com.astro.ai.astroai.model.AstroAppQueryRequestDTO;
import com.astro.ai.astroai.model.aiService.AiServiceMessageDTO;
import com.astro.ai.astroai.model.aiService.AiServiceRequestBodyDTO;
import com.astro.ai.astroai.model.aiService.ChatCompletionDTO;
import com.astro.ai.astroai.model.vedicAstro.VedicAstroDashaResponseDTO;
import com.astro.ai.astroai.model.vedicAstro.VedicAstroPlanetDTO;
import com.astro.ai.astroai.standards.common.AbstractServiceResponse;
import com.astro.ai.astroai.standards.common.GenericResponseDTO;
import com.astro.ai.astroai.standards.entity.ChatGPTCredentials;
import com.astro.ai.astroai.standards.entity.Customer;
import com.astro.ai.astroai.standards.entity.Dasha;
import com.astro.ai.astroai.standards.entity.Planet;
import com.astro.ai.astroai.standards.logging.CustomLogManager;
import com.astro.ai.astroai.standards.logging.CustomLogger;
import com.astro.ai.astroai.standards.logging.LogMessage;
import com.astro.ai.astroai.standards.repository.CustomORM;
import com.astro.ai.astroai.standards.restManger.IRestManager;
import com.astro.ai.astroai.standards.restManger.responses.CustomResponse;
import com.astro.ai.astroai.standards.restManger.responses.RestResponse;
import com.astro.ai.astroai.standards.util.AstroAIConstant;
import com.astro.ai.astroai.standards.util.GlobalUtility;
import com.astro.ai.astroai.utility.AstroAiUtility;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ChatQueryService {

    private CustomLogger logger = CustomLogManager.getLogger(ChatQueryService.class);

    @Value("${VEDIC_ASTRO_API_URL}")
    private String vedicBaseUrl;

    @Value("${CHAT_GPT_URL}")
    private String chatGPTBaseUrl;


    private ObjectMapper mapper = new ObjectMapper();
    {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Autowired
    private  IRestManager restManager;

    public ResponseEntity<AbstractServiceResponse> findResultForChatQuery(JsonNode payload) {

        GenericResponseDTO genericResponseDTO = null;
        try {

            AstroAppQueryRequestDTO queryRequestDTO = mapper.convertValue(payload, AstroAppQueryRequestDTO.class);
            Customer customer = CustomORM.findCustomerAstroAppId(queryRequestDTO.getAstroAiAppId());

            if (customer == null) {

                logger.error(new LogMessage.LogMessageBuilder("ChatQueryService.findResultForChatQuery customer not found in database")
                        .put(AstroAIConstant.PAYLOAD, payload).build());

                genericResponseDTO = GlobalUtility.errorResponse(AstroAIConstant.INTERNAL_SERVER_ERROR);

                return new ResponseEntity<>(genericResponseDTO, genericResponseDTO.getStatus());

            }

            AstroAppPlanetAndDashaDTO astroAppPlanetAndDashaDTO = new AstroAppPlanetAndDashaDTO();
            genericResponseDTO = findPlanetDetails(customer, astroAppPlanetAndDashaDTO);

            String query = queryRequestDTO.getMessage();

            String result = findResultFromAiBot(astroAppPlanetAndDashaDTO.getPlanets(), astroAppPlanetAndDashaDTO.getDashas(), customer, query);

            if (result.equals("Error")) {

                genericResponseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            genericResponseDTO.setMessage(result);

        }catch (Exception e) {

            logger.error(new LogMessage.LogMessageBuilder("ChatQueryService.findResultForChatQuery exception occurred while finding result for chat query")
                    .put(AstroAIConstant.PAYLOAD, payload).build(), e);

            genericResponseDTO = GlobalUtility.errorResponse(AstroAIConstant.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(genericResponseDTO, genericResponseDTO.getStatus());
    }

    private String findResultFromAiBot(List<Planet> planets, List<Dasha> dashas, Customer customer, String query) throws Exception{

        List<String> astrology = AstroAiUtility.CreateAstrology(planets, dashas);

        return findResultFromAiService(astrology, customer, query);

    }

    private String findResultFromAiService(List<String> astrology, Customer customer, String query) throws Exception{

        String firstMessage = "Hi chat GPT i am going to give you four thing First house And PlanetDetails Second planet Detail And Nakshatra Third mahaDasha And AntarDasha Details Fourth A query";

        AiServiceRequestBodyDTO aiServiceRequestBodyDTO = new AiServiceRequestBodyDTO();
        AiServiceMessageDTO initialMessageDTO = new AiServiceMessageDTO();
        initialMessageDTO.setContent(firstMessage);

        AiServiceMessageDTO firstMessageDTO = new AiServiceMessageDTO();
        firstMessageDTO.setContent(astrology.get(0));

        AiServiceMessageDTO secondMessageDTO = new AiServiceMessageDTO();
        secondMessageDTO.setContent(astrology.get(1));

        AiServiceMessageDTO thirdMessageDTO = new AiServiceMessageDTO();
        thirdMessageDTO.setContent(astrology.get(2));

        AiServiceMessageDTO finalMessageDTO = new AiServiceMessageDTO();
        finalMessageDTO.setContent("by analysing all above details answer this " + query);

        aiServiceRequestBodyDTO.setMessages(Arrays.asList(initialMessageDTO, firstMessageDTO, secondMessageDTO, thirdMessageDTO, finalMessageDTO));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<ChatGPTCredentials> chatGPTCredentials = CustomORM.getChatGPTKey();
        headers.set("Authorization", "Bearer " + chatGPTCredentials.get(0).getApiKey());

        int retry = 0;
        Gson gson = new Gson();
        CustomResponse<JsonNode> finalResponse = new RestResponse();
        while (retry < 3) {

            finalResponse = restManager.post(chatGPTBaseUrl, null, headers, gson.toJson(aiServiceRequestBodyDTO), JsonNode.class);

            logger.info(new LogMessage.LogMessageBuilder("ChatQueryService.findResultFromAiService response received for final call")
                    .put(AstroAIConstant.CUSTOMER, customer.getCustomerId())
                    .put(AstroAIConstant.RESPONSE, finalResponse.getBody())
                    .put(AstroAIConstant.REQUEST, aiServiceRequestBodyDTO.toString())
                    .put(AstroAIConstant.HEADERS, headers.toString())
                    .put(AstroAIConstant.ERROR_RESPONSE, finalResponse.getErrorBody()).build());

            if (!finalResponse.getHttpStatus().is2xxSuccessful()) {

                retry ++;
                continue;
            }
                break;
        }

        if (retry == 3) {

            return "Error";
        }

        ChatCompletionDTO chatCompletionDTO = mapper.convertValue(finalResponse.getBody(), ChatCompletionDTO.class);

        return chatCompletionDTO.getChoices()[0].getMessage().getContent();

    }

    private List<Planet> findPlanetDetailByApi(Customer customer) throws Exception{

        String apiKey = AstroAiUtility.findApiKey(customer);

        if (apiKey.equals(AstroAIConstant.EMPTY_STRING)) {

            logger.error(new LogMessage.LogMessageBuilder("ChatQueryService.findPlanetDetailByApi not active api key is found")
                    .put(AstroAIConstant.CUSTOMER, customer).build());

            return null;
        }

        String url = vedicBaseUrl + AstroAIConstant.PLANET_HOROSCOPE_API_PATH;

        MultiValueMap<String, String> params = AstroAiUtility.getVedicAstroApiParams(customer, apiKey);

        CustomResponse<JsonNode> planetDetailResponse = restManager.get(url, params, JsonNode.class);

        logger.info(new LogMessage.LogMessageBuilder("ChatQueryService.findPlanetDetailByApi response received while finding planet Details by api")
                .put(AstroAIConstant.CUSTOMER, customer).put(AstroAIConstant.RESPONSE, planetDetailResponse).build());

        if (!planetDetailResponse.getHttpStatus().is2xxSuccessful()) {

            logger.error(new LogMessage.LogMessageBuilder("ChatQueryService.findPlanetDetailByApi error response received while finding planet Details by api")
                    .put(AstroAIConstant.CUSTOMER, customer).put(AstroAIConstant.RESPONSE, planetDetailResponse).build());

            return null;
        }

        List<VedicAstroPlanetDTO> vedicAstroPlanetDTOList = new ArrayList<>();
        for (int index = 0; index < 10; index++) {
            VedicAstroPlanetDTO vedicAstroPlanetDTO1 = mapper.convertValue(planetDetailResponse.getBody().get("response").get(String.valueOf(index)), VedicAstroPlanetDTO.class);
            vedicAstroPlanetDTOList.add(vedicAstroPlanetDTO1);
        }

        List<Planet> planetList = new ArrayList<>();
        setPlanetDetails(vedicAstroPlanetDTOList, planetList, customer);

        return planetList;
    }

    private void setPlanetDetails(List<VedicAstroPlanetDTO> vedicAstroPlanetDTOList, List<Planet> planetList, Customer customer) {

        for (VedicAstroPlanetDTO vedicAstroPlanetDTO : vedicAstroPlanetDTOList) {

            Planet planet = new Planet();
            planet.setPlanetName(vedicAstroPlanetDTO.getName());
            planet.setPlanetSet(vedicAstroPlanetDTO.isPlanetSet());
            planet.setNakshatra(vedicAstroPlanetDTO.getNakshatra());
            planet.setGlobalDegree(String.valueOf(vedicAstroPlanetDTO.getGlobal_degree()));
            planet.setHouseNumber(vedicAstroPlanetDTO.getHouse());
            planet.setLocalDegree(String.valueOf(vedicAstroPlanetDTO.getLocal_degree()));
            planet.setNakshatraLord(vedicAstroPlanetDTO.getNakshatraLord());
            planet.setPlanetName(vedicAstroPlanetDTO.getFull_name());
            planet.setNakshatraNumber(vedicAstroPlanetDTO.getNakshatraNo());
            planet.setNakshatraPada(vedicAstroPlanetDTO.getNakshatraPada());
            planet.setRasiNumber(vedicAstroPlanetDTO.getRasiNo());
            planet.setZodiac(vedicAstroPlanetDTO.getZodiac());
            planet.setZodiacLord(vedicAstroPlanetDTO.getZodiacLord());
            planet.setCustomer(Arrays.asList(customer));

            CustomORM.savePlanet(planet);

            planetList.add(planet);
        }

    }

    private List<Dasha> findDashaDetailByApi(Customer customer) {

        String apiKey = AstroAiUtility.findApiKey(customer);

        if (apiKey.equals(AstroAIConstant.EMPTY_STRING)) {

            logger.error(new LogMessage.LogMessageBuilder("ChatQueryService.findDashaDetailByApi not active api key is found")
                    .put(AstroAIConstant.CUSTOMER, customer).build());

            return null;
        }

        String dashaUrl = vedicBaseUrl + AstroAIConstant.DASHA_API_PATH;
        MultiValueMap<String, String> params = AstroAiUtility.getVedicAstroApiParams(customer, apiKey);

        CustomResponse<JsonNode> dashaDetailResponse = restManager.get(dashaUrl, params, JsonNode.class);

        logger.info(new LogMessage.LogMessageBuilder("ChatQueryService.findDashaDetailByApi response received while finding Dasha and Antardasha Details by api")
                .put(AstroAIConstant.CUSTOMER, customer).put(AstroAIConstant.RESPONSE, dashaDetailResponse).build());

        if (!dashaDetailResponse.getHttpStatus().is2xxSuccessful()) {

            logger.error(new LogMessage.LogMessageBuilder("ChatQueryService.findPlanetDetailByApi error response received while finding Dasha and Antardasha by api")
                    .put(AstroAIConstant.CUSTOMER, customer).put(AstroAIConstant.RESPONSE, dashaDetailResponse).build());

            return null;
        }

        VedicAstroDashaResponseDTO vedicAstroDashaResponseDTO = mapper.convertValue(dashaDetailResponse.getBody(), VedicAstroDashaResponseDTO.class);

        List<String> firstDashaDetailList = vedicAstroDashaResponseDTO.getResponse().getAntarDashaOrder().get(0);

        int indexOfFirstDasha = AstroAiUtility.findIndexOfDashStartDate(firstDashaDetailList, customer);

        if (indexOfFirstDasha == -1) {

            return null;
        }

        List<Dasha> dashaList = new ArrayList<>();
        boolean flag = true;

        String lastDashaEndDate = null;

        for (int index = indexOfFirstDasha; index < firstDashaDetailList.size(); index ++) {

            Dasha dasha = new Dasha();

            if (flag) {

                dasha.setDashaStartDate(customer.getDateOfBirth().toString());
                flag = false;
            }else {
                dasha.setDashaStartDate(firstDashaDetailList.get(index-1));
            }

            dasha.setDashEndDate(firstDashaDetailList.get(index));
            lastDashaEndDate = dasha.getDashEndDate();
            dasha.setAntarDasha(vedicAstroDashaResponseDTO.getResponse().getAntarDashas().get(0).get(index));
            dasha.setCustomer(customer);

            CustomORM.saveDasha(dasha);
            dashaList.add(dasha);
        }

        for (int index = 1; index < vedicAstroDashaResponseDTO.getResponse().getAntarDashaOrder().size(); index ++) {

            List<String> dashaEndDateList = vedicAstroDashaResponseDTO.getResponse().getAntarDashaOrder().get(index);
            List<String> antarDashaList = vedicAstroDashaResponseDTO.getResponse().getAntarDashas().get(index);

            for (int dashaEndDateIndex = 0; dashaEndDateIndex < dashaEndDateList.size(); dashaEndDateIndex++) {

                String dashaEndDate = dashaEndDateList.get(dashaEndDateIndex);
                Dasha dasha = new Dasha();
                dasha.setDashaStartDate(lastDashaEndDate);
                dasha.setDashEndDate(dashaEndDate);
                lastDashaEndDate = dashaEndDate;
                dasha.setAntarDasha(antarDashaList.get(dashaEndDateIndex));
                dasha.setCustomer(customer);

                CustomORM.saveDasha(dasha);
                dashaList.add(dasha);
            }
        }

        return dashaList;
    }

    private GenericResponseDTO findPlanetDetails(Customer customer, AstroAppPlanetAndDashaDTO astroAppPlanetAndDashaDTO) throws Exception{

        astroAppPlanetAndDashaDTO.setPlanets(customer.getPlanets());
        astroAppPlanetAndDashaDTO.setDashas(CustomORM.findDashaByCustomer(customer));

        GenericResponseDTO genericResponseDTO = GlobalUtility.successResponse();

        if (astroAppPlanetAndDashaDTO.getPlanets() == null || astroAppPlanetAndDashaDTO.getPlanets().size() == 0) {

            List<Planet> planets = findPlanetDetailByApi(customer);

            astroAppPlanetAndDashaDTO.setPlanets(planets);
        }

        if (astroAppPlanetAndDashaDTO.getDashas() == null || astroAppPlanetAndDashaDTO.getDashas().size() == 0) {

            List<Dasha> dashas = findDashaDetailByApi(customer);

            astroAppPlanetAndDashaDTO.setDashas(dashas);
        }

        if (astroAppPlanetAndDashaDTO.getPlanets() == null || astroAppPlanetAndDashaDTO.getDashas() == null) {

            return GlobalUtility.errorResponse(AstroAIConstant.INTERNAL_SERVER_ERROR);
        }

        return genericResponseDTO;

    }


}
