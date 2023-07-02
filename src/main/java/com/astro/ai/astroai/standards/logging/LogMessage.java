package com.astro.ai.astroai.standards.logging;

import com.astro.ai.astroai.standards.util.EncryptUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.message.Message;

import java.util.HashMap;

public class LogMessage implements Message {

    private static final long serialVersionUID = 0;
    private String message;
    private HashMap<Object, Object> extraAttributes = new HashMap<>();
//    private ObjectMapper mapper= new ObjectMapper();

    private ObjectMapper mapper= EncryptUtil.objectMapper();

    private ObjectMapper restMapper = EncryptUtil.objectMapper(true);


    public LogMessage(LogMessageBuilder builder) {
        this.extraAttributes = builder.extraAttributes;
        try {
            this.message = mapper.writeValueAsString(builder.extraAttributes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    public LogMessage(HashMap<Object, Object> extraAttributes) {
        try {
            this.message = restMapper.writeValueAsString(extraAttributes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static class LogMessageBuilder {

        private HashMap<Object, Object> extraAttributes = new HashMap<>();

        public LogMessageBuilder(String message) {
            this.extraAttributes.put("Message", message);
        }

        public LogMessageBuilder() {
        }

        public LogMessageBuilder put(Object key, Object value) {
            this.extraAttributes.put(key, value);
            return this;
        }

        public LogMessageBuilder put(Throwable t) {
            if(t == null) {
                this.extraAttributes.put("exception", "Unknown");
                this.extraAttributes.put("exception_message", "Unknown");
            } else {
                this.extraAttributes.put("exception", t.getClass().getName());
                this.extraAttributes.put("exception_message", t.getMessage());
            }
            return this;
        }

        public LogMessage build() {
            LogMessage logMessage = new LogMessage(this);
            return logMessage;
        }

        public LogMessage restBuild()  {
            LogMessage logMessage = new LogMessage(this.extraAttributes);
            return logMessage;
        }
    }

    @Override
    public String getFormattedMessage() {
        return message;
    }

    @Override
    public String getFormat() {
        return message;
    }

    @Override
    public Throwable getThrowable() {
        return null;
    }

    @Override
    public Object[] getParameters() {
        return null;
    }


}
