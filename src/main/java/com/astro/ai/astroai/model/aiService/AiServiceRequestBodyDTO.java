package com.astro.ai.astroai.model.aiService;

import java.util.List;
import java.util.Objects;

public class AiServiceRequestBodyDTO {

    private String model = "gpt-3.5-turbo";

    private List<AiServiceMessageDTO> messages;

    private double temperature = 0.7;

    public AiServiceRequestBodyDTO() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<AiServiceMessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<AiServiceMessageDTO> messages) {
        this.messages = messages;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AiServiceRequestBodyDTO that = (AiServiceRequestBodyDTO) o;
        return Double.compare(that.temperature, temperature) == 0 && Objects.equals(model, that.model) && Objects.equals(messages, that.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, messages, temperature);
    }

    @Override
    public String toString() {
        return "AiServiceRequestBodyDTO{" +
                "model='" + model + '\'' +
                ", messages=" + messages +
                ", temperature=" + temperature +
                '}';
    }
}
