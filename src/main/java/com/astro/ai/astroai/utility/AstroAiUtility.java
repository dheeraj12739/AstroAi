package com.astro.ai.astroai.utility;

import com.astro.ai.astroai.service.ChatQueryService;
import com.astro.ai.astroai.standards.entity.Customer;
import com.astro.ai.astroai.standards.entity.Dasha;
import com.astro.ai.astroai.standards.entity.Planet;
import com.astro.ai.astroai.standards.entity.PlanetDetailsCredentials;
import com.astro.ai.astroai.standards.logging.CustomLogManager;
import com.astro.ai.astroai.standards.logging.CustomLogger;
import com.astro.ai.astroai.standards.logging.LogMessage;
import com.astro.ai.astroai.standards.repository.CustomORM;
import com.astro.ai.astroai.standards.util.AstroAIConstant;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AstroAiUtility {

    private static CustomLogger logger = CustomLogManager.getLogger(ChatQueryService.class);

    public static String findApiKey(Customer customer) {

        List<PlanetDetailsCredentials> planetDetailsCredentialsList = CustomORM.findPlanetDetailsCredentialsByIsActive(true);

        String apiKey = "";
        for (PlanetDetailsCredentials planetDetailsCredentials : planetDetailsCredentialsList) {

            if(planetDetailsCredentials.getActive()) {

                apiKey = planetDetailsCredentials.getApiKey();
                break;
            }
        }

        return apiKey;
    }

    public static MultiValueMap<String, String> getVedicAstroApiParams(Customer customer, String apiKey) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(AstroAIConstant.API_KEY, apiKey);
        params.add(AstroAIConstant.DATE_OF_BIRTH, customer.getDateOfBirth().toString());
        params.add(AstroAIConstant.TIME_OF_BIRTH, customer.getTimeOfBirth());
        params.add(AstroAIConstant.LANGUAGE, "en");
        params.add(AstroAIConstant.LATITUDE, customer.getLatitude());
        params.add(AstroAIConstant.LONGITUDE, customer.getLongitude());
        params.add(AstroAIConstant.TIME_ZONE, "5.5");

        return params;
    }

    public static int findIndexOfDashStartDate(List<String> firstDashaDetailList, Customer customer) {

        String cutomerDateInString = String.valueOf(customer.getDateOfBirth());
        String[] parts = cutomerDateInString.split("/");
        String customerYear = parts[2];

        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd yyyy");
        int index = 0;

        for (String dateInString : firstDashaDetailList) {

            try {

                Date date = inputFormat.parse(dateInString);
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy");
                String dashaYear = outputFormat.format(date);

                if (!(Long.parseLong(customerYear) >= Long.parseLong(dashaYear))) {
                    break;
                }
                index ++;

            }catch (Exception e) {

                logger.error(new LogMessage.LogMessageBuilder("AstroAiUtility.findIndexOfDashStartDate exception occurred while parsing date").put("FirsDashaDetail", firstDashaDetailList).put(AstroAIConstant.CUSTOMER, customer).build());
                index = -1;
                break;
            }

        }

        return index;
    }

    public static List<String> CreateAstrology(List<Planet> planets, List<Dasha> dashas) throws Exception{

        String houseAndPlanetDetails = AstroAiUtility.getHouseAndPlanetDetails(planets);
        String planetDetailAndNakshatra = AstroAiUtility.getPlanetDetailsAndNakshatra(planets);
        String mahaDashaAndAntarDashaDetails = AstroAiUtility.getMahaDashaAndAntarDashaDetails(dashas);

        List<String> astrology = new ArrayList<>();
        astrology.add(houseAndPlanetDetails);
        astrology.add(planetDetailAndNakshatra);
        astrology.add(mahaDashaAndAntarDashaDetails);

        return astrology;
    }

    private static String getMahaDashaAndAntarDashaDetails(List<Dasha> dashas) throws Exception{

        List<String> mahaDashaList = AstroAiUtility.findMahaDashaList(dashas);
        Map<String, List<Date>>  mahaDashaStartEndDate = AstroAiUtility.findMahaDashaStartAndEndDate(dashas, mahaDashaList);
        StringBuilder dashaAndMahaDasha = new StringBuilder();
        int index = 1;
        for (Map.Entry<String, List<Date>> entry :mahaDashaStartEndDate.entrySet()) {

            String key = entry.getKey();
            List<Date> value = entry.getValue();
            dashaAndMahaDasha.append(AstroAIConstant.NUMBER_TO_STRING.get(index))
                    .append("mahadasha is of ")
                    .append(key)
                    .append(" planet which starts from ")
                    .append(value.get(0))
                    .append(" and ending at ").
                    append(value.get(1)).append("\n");
            index++;
            findAntartDashaPeriodOfPlanet(key, dashaAndMahaDasha, dashas);
        }

        return dashaAndMahaDasha.toString();
    }

    private static void findAntartDashaPeriodOfPlanet(String key, StringBuilder dashaAndMahaDasha, List<Dasha> dashas) {

        dashaAndMahaDasha.append("In the mahadasa of ").append(key).append("\n");

        for (Dasha dasha :dashas) {
            String []dashaName = dasha.getAntarDasha().split("/");
            if (dashaName[0].equals(key)) {

                dashaAndMahaDasha.append("Antaradasa of ")
                        .append(dashaName[1])
                        .append(" planet starts from ")
                        .append(dasha.getDashaStartDate())
                        .append(" and ends at ")
                        .append(dasha.getDashEndDate())
                        .append("\n");
            }
        }
    }

    private static Map<String, List<Date>> findMahaDashaStartAndEndDate(List<Dasha> dashas, List<String> mahaDashaList) throws Exception {

        Map<String, List<Date>> mahaDashaStartEndDate = new HashMap<>();

        String startDate = "Mon Feb 10 5000";
        String endDate = "Mon Feb 10 1950";

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd yyyy");
        Date parsedStartDate = dateFormat.parse(startDate);
        Date parsedEndDate = dateFormat.parse(endDate);

        for (String mahaDasha : mahaDashaList) {

            mahaDashaStartEndDate.put(mahaDasha, new ArrayList<>());
            mahaDashaStartEndDate.get(mahaDasha).add(parsedStartDate);
            mahaDashaStartEndDate.get(mahaDasha).add(parsedEndDate);
        }

        String outputDateStringOne;
        String outputDateStringTwo;
        DateTimeFormatter inputDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Dasha dasha : dashas) {

            String[] combinedDasha = dasha.getAntarDasha().split(AstroAIConstant.FORWARD_SLASH);

            outputDateStringOne = dasha.getDashaStartDate();

            if (outputDateStringOne.contains("/")) {

                LocalDate inputDate = LocalDate.parse(outputDateStringOne, inputDateFormatter);

                // Create ZonedDateTime object with desired date and time
                ZonedDateTime zonedDateTime = ZonedDateTime.of(inputDate.atStartOfDay(), ZoneId.systemDefault());

                // Format ZonedDateTime object into desired output format
                DateTimeFormatter outputDateFormatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy", Locale.US);
                outputDateStringOne = zonedDateTime.format(outputDateFormatter);
            }



            if ((mahaDashaStartEndDate.get(combinedDasha[0]).get(0).compareTo(dateFormat.parse(outputDateStringOne)) > 0)) {

                mahaDashaStartEndDate.get(combinedDasha[0]).set(0, dateFormat.parse(outputDateStringOne));
            }

            outputDateStringTwo = dasha.getDashEndDate();

            if (outputDateStringTwo.contains("/")) {

                LocalDate inputDateSecond = LocalDate.parse(outputDateStringTwo, inputDateFormatter);

                // Create ZonedDateTime object with desired date and time
                ZonedDateTime zonedDateTimeSecond = ZonedDateTime.of(inputDateSecond.atStartOfDay(), ZoneId.systemDefault());

                // Format ZonedDateTime object into desired output format
                DateTimeFormatter outputDateFormatterSecond = DateTimeFormatter.ofPattern("EEE MMM dd yyyy", Locale.US);

                outputDateStringTwo = zonedDateTimeSecond.format(outputDateFormatterSecond);
            }

            if ( !(mahaDashaStartEndDate.get(combinedDasha[0]).get(1).compareTo(dateFormat.parse(outputDateStringTwo)) > 0)) {

                mahaDashaStartEndDate.get(combinedDasha[0]).set(1, dateFormat.parse(dasha.getDashEndDate()));
            }
        }

        return mahaDashaStartEndDate;
    }

    private static List<String> findMahaDashaList(List<Dasha> dashas) {

        Set<String> mahaDashaSet = new HashSet<>();
        List<String> dashaListToReturn = new ArrayList<>();

        for (Dasha dasha : dashas) {
            String[] combinedDasha = dasha.getAntarDasha().split(AstroAIConstant.FORWARD_SLASH);
            mahaDashaSet.add(combinedDasha[0]);
        }

        dashaListToReturn.addAll(mahaDashaSet);

        return dashaListToReturn;
    }

    private static String getPlanetDetailsAndNakshatra(List<Planet> planets) {
        StringBuilder planetDetailAndNakshatra = new StringBuilder();

        for (Planet planet : planets) {
            planetDetailAndNakshatra.append(planet.getPlanetName())
                    .append(" is at ")
                    .append(planet.getLocalDegree())
                    .append(" degree at ")
                    .append(planet.getNakshatra())
                    .append(" nakshtra.")
                    .append("\n");
        }

        return planetDetailAndNakshatra.toString();
    }

    private static String getHouseAndPlanetDetails(List<Planet> planets) throws Exception{
        int ascendantRasiNumber = AstroAiUtility.findAscedentRasiNumber(planets);
        StringBuilder houseAndPlanetDetails = new StringBuilder();

        for (int index = 1; index < 13; index++) {
            houseAndPlanetDetails.append(AstroAIConstant.NUMBER_TO_STRING.get(index))
                    .append("House is ")
                    .append(AstroAIConstant.RASINUMBER_TO_RASINAME.get((ascendantRasiNumber + index -2) % 12))
                    .append(" and has ");

            List<String> planetsName = AstroAiUtility.findPlanetBYHouseNUmber(index, planets);

            for (String planetName : planetsName) {
                houseAndPlanetDetails.append(planetName).append(AstroAIConstant.COMMA);
            }
            houseAndPlanetDetails.append("\n");
        }

        return houseAndPlanetDetails.toString();
    }


    private static List<String> findPlanetBYHouseNUmber(int houseNumber, List<Planet> planets) {

        List<String> planetsName = new ArrayList<>();
        for (Planet planet : planets) {

            if ((planet.getHouseNumber() == houseNumber) && (! planet.getPlanetName().equals(AstroAIConstant.ASCENDANT))) {

                planetsName.add(planet.getPlanetName());
            }
        }

        if (planetsName.size() == 0) {

            planetsName.add(AstroAIConstant.NO_PLANET);
        }
        return planetsName;
    }

    private static int findAscedentRasiNumber(List<Planet> planets) {

        int rasiNUmber = 0;
        for (Planet planet : planets) {

            if (planet.getPlanetName().equals(AstroAIConstant.ASCENDANT)) {

                rasiNUmber = planet.getRasiNumber();
            }
        }

        return rasiNUmber;
    }
}
