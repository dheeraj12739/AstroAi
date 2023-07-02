package com.astro.ai.astroai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
public class AstroAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AstroAiApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplateBean(RestTemplateBuilder restTemplateBuilder) {

        return restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(60000))
                .setReadTimeout(Duration.ofMillis(60000))
                .build();
    }

}
