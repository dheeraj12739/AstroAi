package com.astro.ai.astroai.model.vedicAstro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VedicAstroHoroscopePlanetResponseDTO {

    @JsonProperty("status")
    private int status;

    @JsonProperty("response")
    List<VedicAstroPlanetDTO> response;

    public VedicAstroHoroscopePlanetResponseDTO() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<VedicAstroPlanetDTO> getResponse() {
        return response;
    }

    public void setResponse(List<VedicAstroPlanetDTO> response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VedicAstroHoroscopePlanetResponseDTO that = (VedicAstroHoroscopePlanetResponseDTO) o;
        return status == that.status && Objects.equals(response, that.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, response);
    }

    @Override
    public String toString() {
        return "VedicAstroHoroscopePlanetResponseDTO{" +
                "status=" + status +
                ", response=" + response +
                '}';
    }
}
