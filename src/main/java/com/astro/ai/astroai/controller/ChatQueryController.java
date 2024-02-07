package com.astro.ai.astroai.controller;

import com.astro.ai.astroai.service.ChatQueryService;
import com.astro.ai.astroai.service.DemoService;
import com.astro.ai.astroai.standards.common.AbstractServiceResponse;
import com.astro.ai.astroai.standards.logging.CustomLogManager;
import com.astro.ai.astroai.standards.logging.CustomLogger;
import com.astro.ai.astroai.standards.logging.LogMessage;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("astro/ai")
public class ChatQueryController {

    private CustomLogger logger = CustomLogManager.getLogger(ChatQueryController.class);

    @Autowired
    private ChatQueryService chatQueryService;

    @Autowired
    private DemoService demoService;

    @PostMapping(value = "/chatquery", headers = "Accept=application/json")
    public ResponseEntity<AbstractServiceResponse> getChatQueryResult(@RequestBody JsonNode payload) {

        logger.info(new LogMessage.LogMessageBuilder("ChatQueryController.getChatQueryResult => called with payload")
                .put("payload", payload)
                .build());

        demoService.minJumps();

        return chatQueryService.findResultForChatQuery(payload);
    }

    @RequestMapping("/ping")
    public String ping() {
        System.out.println("Test controller");
        return new Date().toString();
    }


}
