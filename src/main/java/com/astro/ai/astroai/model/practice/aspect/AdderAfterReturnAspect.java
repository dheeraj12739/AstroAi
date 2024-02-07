package com.astro.ai.astroai.model.practice.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdderAfterReturnAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void afterReturn(Object returnValue) throws Throwable {

        logger.info("return value is {}", returnValue);
    }
}
