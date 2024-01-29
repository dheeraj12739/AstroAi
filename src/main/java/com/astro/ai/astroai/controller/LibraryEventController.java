package com.astro.ai.astroai.controller;

import com.astro.ai.astroai.libraryEvent.LibraryEvent;
import com.astro.ai.astroai.libraryEvent.LibraryEventType;
//import com.astro.ai.astroai.libraryEvent.producer.LibraryEventProducer;
import com.astro.ai.astroai.standards.logging.CustomLogManager;
import com.astro.ai.astroai.standards.logging.CustomLogger;
import com.astro.ai.astroai.standards.logging.LogMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping(value = "/event")
@Slf4j
public class LibraryEventController {

//    @Autowired
//    LibraryEventProducer libraryEventProducer;

    int count =1;
    private CustomLogger logger = CustomLogManager.getLogger(LibraryEventController.class);

    @PostMapping("/create")
    @RateLimiter(name = "test" , fallbackMethod = "createEventFallback")
    public ResponseEntity<LibraryEvent> createEvent(@RequestBody LibraryEvent libraryEvent)
            throws JsonProcessingException, ExecutionException, InterruptedException {

        logger.info(new LogMessage.LogMessageBuilder("LibraryEventController.createEvent method called for rate limit times:" + count)
                .put("Payload", "TEST").build());

        count++;

        libraryEvent.setEventType(LibraryEventType.NEW);
        //libraryEventProducer.sendLibrarySendApproach2(libraryEvent);

        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }

    public ResponseEntity<LibraryEvent> createEventFallback(LibraryEvent libraryEvent, Throwable t) {
        // Log the error or take necessary actions
        logger.error(new LogMessage.LogMessageBuilder("Fallback triggered for createEvent").build());

        // Return a default value or an appropriate response
        return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .body(new LibraryEvent()); // Example: Return a default or an error-specific object
    }

    @PutMapping("/create")
    public ResponseEntity<LibraryEvent> updateEvent(@RequestBody LibraryEvent libraryEvent)
            throws JsonProcessingException, ExecutionException, InterruptedException {

        if(libraryEvent.getEventId() == null) {

            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NO ENTITY EXIST");
        }
        libraryEvent.setEventType(LibraryEventType.NEW);
        //libraryEventProducer.sendLibrarySendApproach2(libraryEvent);

        return ResponseEntity.status(HttpStatus.OK).body(libraryEvent);
    }

    @GetMapping("/label")
    public String getLabel() {

        System.out.println("inside the get label");

        return "Lotte-lable";
    }
}
