package com.astro.ai.astroai.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class AstroAppQueryRequestDTO {

    @JsonProperty("astroAiAppId")
    private String astroAiAppId;

    @JsonProperty("message")
    private String message;

    public AstroAppQueryRequestDTO() {
    }

    public String getAstroAiAppId() {
        return astroAiAppId;
    }

    public void setAstroAiAppId(String astroAiAppId) {
        this.astroAiAppId = astroAiAppId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AstroAppQueryRequestDTO that = (AstroAppQueryRequestDTO) o;
        return Objects.equals(astroAiAppId, that.astroAiAppId) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(astroAiAppId, message);
    }

    @Override
    public String toString() {
        return "AstroAiAppQueryRequestDTO{" +
                "astroAiAppId='" + astroAiAppId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
