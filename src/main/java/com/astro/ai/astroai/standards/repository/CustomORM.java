package com.astro.ai.astroai.standards.repository;

import com.astro.ai.astroai.standards.entity.*;
import com.astro.ai.astroai.standards.logging.CustomLogManager;
import com.astro.ai.astroai.standards.logging.CustomLogger;
import com.astro.ai.astroai.standards.logging.LogMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomORM {


    private static CustomerRepository customerRepository;

    private static PlanetRepository planetRepository;

    private static PlanetDetailsCredentialRepository planetDetailsCredentialRepository;

    private static DashaRepository dashaRepository;

    private static ChatGPTCredentialsRepository chatGPTCredentialsRepository;


    private static CustomLogger logger = CustomLogManager.getLogger(CustomORM.class);

    public static List<Dasha> findDashaByCustomer(Customer customer) {

        return dashaRepository.findDashaByCustomer(customer);
    }

    public static Dasha saveDasha(Dasha dasha) {

        return dashaRepository.save(dasha);
    }


    @Autowired
    private void setCustomerRepository(CustomerRepository customerRepository) {

        CustomORM.customerRepository = customerRepository;
    }

    @Autowired
    public  void setChatGPTCredentialsRepository(ChatGPTCredentialsRepository chatGPTCredentialsRepository) {
        CustomORM.chatGPTCredentialsRepository = chatGPTCredentialsRepository;
    }


    @Autowired
    private void setPlanetRepository(PlanetRepository planetRepository) {

        CustomORM.planetRepository = planetRepository;
    }

    @Autowired
    public  void setPlanetDetailsCredentialRepository(PlanetDetailsCredentialRepository planetDetailsCredentialRepository) {
        CustomORM.planetDetailsCredentialRepository = planetDetailsCredentialRepository;
    }

    @Autowired
    public  void setDashaRepository(DashaRepository dashaRepository) {

        CustomORM.dashaRepository = dashaRepository;
    }

    public static List<ChatGPTCredentials> getChatGPTKey() {

        return chatGPTCredentialsRepository.findChatGPTCredentialsByActive(true);
    }

    public static Customer saveCustomerInDb(Customer customer) {

        Customer customerToReturn = null;
        try {

            Customer savedCustomer = customerRepository.save(customer);

            logger.info(new LogMessage.LogMessageBuilder("CustomORM.saveCustomerInDb customer saved in Database")
                    .put("Customer", customer).build());

            customerToReturn = savedCustomer;
        }catch (Exception e) {

            logger.error(new LogMessage.LogMessageBuilder("CustomORM.saveCustomerInDb exception occurred while saving customer in database")
                    .put("Customer", customer).build());

        }
        return customer;
    }

    public static Customer findCustomerAstroAppId(String astroAiAppId) {

        return customerRepository.findByAstroAppCustomerId(astroAiAppId);

    }

    public static List<PlanetDetailsCredentials> findPlanetDetailsCredentialsByIsActive(Boolean isActive) {

        return planetDetailsCredentialRepository.findPlanetDetailsCredentialsByActive(isActive);
    }

    public static Planet savePlanet(Planet planet) {

        return planetRepository.save(planet);
    }
}
