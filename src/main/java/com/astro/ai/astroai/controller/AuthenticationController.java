package com.astro.ai.astroai.controller;

import com.astro.ai.astroai.service.AuthenticationService;
import com.astro.ai.astroai.standards.common.AbstractServiceResponse;
import com.astro.ai.astroai.standards.logging.CustomLogManager;
import com.astro.ai.astroai.standards.logging.CustomLogger;
import com.astro.ai.astroai.standards.logging.LogMessage;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/atro/ai")
public class AuthenticationController {

    private CustomLogger logger = CustomLogManager.getLogger(AuthenticationController.class);


    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {

        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AbstractServiceResponse> registerCustomer(@RequestBody JsonNode payload) {

        logger.info(new LogMessage.LogMessageBuilder("AuthenticationController.registerCustomer register customer called with payload")
                .put("Payload", payload).build());

        return authenticationService.registerCustomer(payload);
    }
}
