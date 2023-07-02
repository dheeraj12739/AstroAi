package com.astro.ai.astroai.standards.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomLogManager {

    public static CustomLogger getLogger(final Class<?> clazz){
        CustomLogger customLogger =new CustomLogger();
        Logger logger= LogManager.getLogger(clazz);
        customLogger.logger= logger;
        return customLogger;
    }

}
