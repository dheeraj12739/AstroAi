package com.astro.ai.astroai.standards.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

public class CustomLogger {

    public Logger logger;

    public enum ELogLevel{
        INFO,DEBUG,TRACE,WARN,ERROR
    }

    private Level getLogLevel(ELogLevel logLevel){

        switch(logLevel){
            case INFO: return Level.INFO;
            case DEBUG: return Level.DEBUG;
            case TRACE: return Level.TRACE;
            case WARN: return Level.WARN;
            case ERROR: return Level.ERROR;
            default: return Level.INFO;
        }
    }

    @Deprecated
    public boolean printLog(ELogLevel logLevel, LogMessage message, Throwable e){
        try {
            this.logger.log(getLogLevel(logLevel), message, e);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Deprecated
    public boolean printLog(ELogLevel logLevel, LogMessage message){
        try {
            this.logger.log(getLogLevel(logLevel),message);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }


    public boolean error(LogMessage message, Throwable e){
        try {
            this.logger.log(getLogLevel(ELogLevel.ERROR),message, e);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public boolean error(LogMessage message){
        try {
            this.logger.log(getLogLevel(ELogLevel.ERROR),message);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public boolean info(LogMessage message){
        try {
            this.logger.log(getLogLevel(ELogLevel.INFO),message);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public boolean debug(LogMessage message){
        try {
            this.logger.log(getLogLevel(ELogLevel.DEBUG),message);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public boolean warn(LogMessage message){
        try {
            this.logger.log(getLogLevel(ELogLevel.WARN),message);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }
}
