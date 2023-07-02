package com.astro.ai.astroai.model.vedicAstro;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class VedicAstroDashaResponseDTO {

    @JsonProperty("status")
    private int status;

    @JsonProperty("response")
    private DashaResponseData response;

    public VedicAstroDashaResponseDTO() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DashaResponseData getResponse() {
        return response;
    }

    public void setResponse(DashaResponseData response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VedicAstroDashaResponseDTO that = (VedicAstroDashaResponseDTO) o;
        return status == that.status && Objects.equals(response, that.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, response);
    }

    @Override
    public String toString() {
        return "VedicAstroDashaResponseDTO{" +
                "status=" + status +
                ", response=" + response +
                '}';
    }
}
