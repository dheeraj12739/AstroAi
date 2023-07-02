package com.astro.ai.astroai.standards.common;

import org.springframework.http.HttpStatus;

import java.util.Objects;

public class GenericResponseDTO extends AbstractServiceResponse{

    private HttpStatus status;

    private String message;

    private String summary;

    public GenericResponseDTO() {
    }

    public GenericResponseDTO(HttpStatus status, String message, String summary) {
        this.status = status;
        this.message = message;
        this.summary = summary;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericResponseDTO that = (GenericResponseDTO) o;
        return status == that.status && Objects.equals(message, that.message) && Objects.equals(summary, that.summary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message, summary);
    }

    @Override
    public String toString() {
        return "GenericResponseDTO{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}
