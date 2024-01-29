package com.astro.ai.astroai.libraryEvent.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

//@Component
//@Slf4j
//public class LibraryEventConsumer implements AcknowledgingMessageListener<Integer, String> {

//    //@KafkaListener(topics = {"library-events"})
//    public void onMessageFirst(ConsumerRecord<Integer, String> consumerRecord) throws InterruptedException {
//
//        log.info("consumer started for onMessageFirst: {}" ,Thread.currentThread().getId());
//
//        log.info("consumed record onMessageFirst: {}", consumerRecord);
//
//        Thread.sleep(120 * 1000);
//
//        log.info("wait is done and the for onMessageFirst id is: {} ",Thread.currentThread().getId());
//    }
//
//
//    @Override
//   // @KafkaListener(topics = {"library-events"})
//    public void onMessage(ConsumerRecord<Integer, String> consumerRecord, Acknowledgment acknowledgment) {
//
//        log.info("consumer started for onMessageFirst: {}" ,Thread.currentThread().getId());
//
//        log.info("consumed record onMessageFirst: {}", consumerRecord);
//
//        try {
//            Thread.sleep(120 * 1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        log.info("wait is done and the for onMessageFirst id is: {} ",Thread.currentThread().getId());
//
//        acknowledgment.acknowledge();
//
//    }
//}
