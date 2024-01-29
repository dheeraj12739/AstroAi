//package com.astro.ai.astroai.libraryEvent.producer;
//
//import com.astro.ai.astroai.libraryEvent.LibraryEvent;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.log4j.Log4j2;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.apache.kafka.common.header.Header;
//import org.apache.kafka.common.header.internals.RecordHeader;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.SendResult;
//import org.springframework.stereotype.Component;
//import org.springframework.util.concurrent.ListenableFuture;
//import org.springframework.util.concurrent.ListenableFutureCallback;
//
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//
//@Log4j2
//@Component
//public class LibraryEventProducer {
//
//    @Autowired
//    KafkaTemplate<Integer, String> kafkaTemplate;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    private String topic = "library-events";
//
//    public void sendLibrarySend(LibraryEvent libraryEvent) throws JsonProcessingException {
//
//        Integer key = libraryEvent.getEventId();
//        String value = objectMapper.writeValueAsString(libraryEvent);
//
//        ListenableFuture<SendResult<Integer, String>> listenableFuture = kafkaTemplate.send("library-events", key, value);
//        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
//            @Override
//            public void onFailure(Throwable ex) {
//
//                handleFailure(key, value, ex);
//            }
//
//            @Override
//            public void onSuccess(SendResult<Integer, String> result) {
//
//                handleSuccess(key, value, result);
//            }
//        });
//
//    }
//
//    public SendResult<Integer, String> sendLibrarySendSyncronus(LibraryEvent libraryEvent) throws JsonProcessingException, ExecutionException, InterruptedException {
//
//        Integer key = libraryEvent.getEventId();
//        String value = objectMapper.writeValueAsString(libraryEvent);
//
//        SendResult<Integer, String> sendResult = kafkaTemplate.send("library-events", key, value).get();
//
//        return sendResult;
//
//    }
//
//    public void sendLibrarySendApproach2(LibraryEvent libraryEvent) throws JsonProcessingException {
//
//        Integer key = libraryEvent.getEventId();
//        String value = objectMapper.writeValueAsString(libraryEvent);
//
//        ProducerRecord<Integer, String> producerRecord = buildProducerRecord(key, value);
//
//        ListenableFuture<SendResult<Integer, String>> listenableFuture = kafkaTemplate.send(producerRecord);
//        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
//            @Override
//            public void onFailure(Throwable ex) {
//
//                handleFailure(key, value, ex);
//            }
//
//            @Override
//            public void onSuccess(SendResult<Integer, String> result) {
//
//                handleSuccess(key, value, result);
//            }
//        });
//
//    }
//
//    private ProducerRecord<Integer, String>  buildProducerRecord(Integer key, String value) {
//
//
//        List<Header> headers = List.of(new RecordHeader("event-source", "event date".getBytes()));
//
//        return new ProducerRecord<>(topic, null, key, value, headers);
//    }
//
//    private void handleFailure(Integer key, String value, Throwable ex) {
//
//        log.error("eeror sending the message and the exception is {}", ex.getMessage());
//
//        try {
//            throw ex;
//        }catch (Throwable throwable) {
//
//            log.error("Error or in failure : {}", throwable.getMessage());
//        }
//    }
//
//    private void handleSuccess(Integer key, String value, SendResult<Integer, String> result) {
//
//        log.info("message send successfully for the key: {} and the value is: {} and the partition is: {}",
//                key, value, result.getRecordMetadata().partition());
//
//
//    }
//}
