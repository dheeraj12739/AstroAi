package com.astro.ai.astroai.service;

import com.astro.ai.astroai.model.AstroAppCustomerPayloadDTO;
import com.astro.ai.astroai.standards.common.AbstractServiceResponse;
import com.astro.ai.astroai.standards.common.GenericResponseDTO;
import com.astro.ai.astroai.standards.entity.Customer;
import com.astro.ai.astroai.standards.logging.CustomLogManager;
import com.astro.ai.astroai.standards.logging.CustomLogger;
import com.astro.ai.astroai.standards.logging.LogMessage;
import com.astro.ai.astroai.standards.repository.CustomORM;
import com.astro.ai.astroai.standards.util.GlobalUtility;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

    private final CustomLogger logger = CustomLogManager.getLogger(AuthenticationService.class);

    ObjectMapper mapper = new ObjectMapper();

    public ResponseEntity<AbstractServiceResponse> registerCustomer(JsonNode payload) {

        GenericResponseDTO genericResponseDTO = GlobalUtility.successResponse();
        try {

            AstroAppCustomerPayloadDTO payloadDTO = mapper.convertValue(payload, AstroAppCustomerPayloadDTO.class);

            Customer customer = GlobalUtility.createCustomerToBeSavedInDb(payloadDTO);
            CustomORM.saveCustomerInDb(customer);

        }catch (Exception e) {

            logger.error(new LogMessage.LogMessageBuilder("AuthenticationService.registerCustomer exception occurred while registering")
                    .put("Payload", payload).build(), e);

            genericResponseDTO = GlobalUtility.errorResponse("Internal Server Error!");
        }
        return new ResponseEntity<>(genericResponseDTO, genericResponseDTO.getStatus());
    }

}
