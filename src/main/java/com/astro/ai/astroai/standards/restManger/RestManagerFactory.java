package com.astro.ai.astroai.standards.restManger;

import com.astro.ai.astroai.standards.ERestManager;
import com.astro.ai.astroai.standards.logging.CustomLogManager;
import com.astro.ai.astroai.standards.logging.CustomLogger;
import com.astro.ai.astroai.standards.logging.LogMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("restManagerFactory")
public class RestManagerFactory {

    private static RestTemplateManager restTemplateManager;

    @Autowired
    public void setRestTemplateManager(RestTemplateManager restTemplateManager) {
        RestManagerFactory.restTemplateManager = restTemplateManager;
    }

    public static RestTemplateManager getRestTemplateManager() {
        return restTemplateManager;
    }

    public static IRestManager getInstance(ERestManager eRestManager) {

        switch (eRestManager) {

            case REST_TEMPLATE:
                return restTemplateManager;

            default: {
                CustomLogger logger = CustomLogManager.getLogger(RestManagerFactory.class);
                logger.error(new LogMessage.LogMessageBuilder("This Rest Manager is not supported").build());
                throw new IllegalArgumentException();
            }
        }
    }
}
