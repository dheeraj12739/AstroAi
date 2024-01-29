package com.astro.ai.astroai.standards.util;

import com.astro.ai.astroai.model.AstroAppCustomerPayloadDTO;
import com.astro.ai.astroai.standards.common.GenericResponseDTO;
import com.astro.ai.astroai.standards.entity.ChatGPTCredentials;
import com.astro.ai.astroai.standards.entity.Customer;
import com.astro.ai.astroai.standards.repository.CustomORM;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GlobalUtility {

    public static GenericResponseDTO successResponse() {

        GenericResponseDTO genericResponseDTO = new GenericResponseDTO();
        genericResponseDTO.setStatus(HttpStatus.OK);
        genericResponseDTO.setMessage("Customer Created Successfully!");
        genericResponseDTO.setSummary("Success!");

        return genericResponseDTO;
    }

    public static GenericResponseDTO errorResponse(String message) {

        GenericResponseDTO genericResponseDTO = new GenericResponseDTO();
        genericResponseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        genericResponseDTO.setSummary("Something Went Wrong!");
        genericResponseDTO.setMessage(message);

        return genericResponseDTO;

    }

    public static Customer createCustomerToBeSavedInDb(AstroAppCustomerPayloadDTO payloadDTO) throws Exception{

        Customer customer = new Customer();
        customer.setAstroAppCustomerId(payloadDTO.getCustomerDetail().getAstroAiAppId());
        customer.setLatitude(payloadDTO.getCustomerDetail().getLatitude());
        customer.setLongitude(payloadDTO.getCustomerDetail().getLongitude());
        customer.setDateOfBirth(payloadDTO.getCustomerDetail().getDateOfBirth());
        customer.setTimeOfBirth(payloadDTO.getCustomerDetail().getTimeOfBirth());

        return customer;
    }

    public static HttpHeaders getChatGPTHeader(){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<ChatGPTCredentials> chatGPTCredentials = CustomORM.getChatGPTKey();
        headers.set("Authorization", "Bearer " + chatGPTCredentials.get(0).getApiKey());

        return headers;
    }
}
