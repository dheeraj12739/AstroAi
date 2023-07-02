package com.astro.ai.astroai.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class AstroAppCustomerDetailDTO {
    @JsonProperty("astroAiAppId")
    private String astroAiAppId;
    @JsonProperty("dateOfBirth")
    private String dateOfBirth;
    @JsonProperty("timeOfBirth")
    private String timeOfBirth;
    @JsonProperty("latitude")
    private String latitude;
    @JsonProperty("longitude")
    private String longitude;

    //private String timzone to be added after confirmation


    public AstroAppCustomerDetailDTO() {
    }

    public String getAstroAiAppId() {
        return astroAiAppId;
    }

    public void setAstroAiAppId(String astroAiAppId) {
        this.astroAiAppId = astroAiAppId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTimeOfBirth() {
        return timeOfBirth;
    }

    public void setTimeOfBirth(String timeOfBirth) {
        this.timeOfBirth = timeOfBirth;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {

            return true;
        }
        if (o == null || getClass() != o.getClass()) {

            return false;
        }
        AstroAppCustomerDetailDTO that = (AstroAppCustomerDetailDTO) o;
        return Objects.equals(astroAiAppId, that.astroAiAppId) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(timeOfBirth, that.timeOfBirth) && Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(astroAiAppId, dateOfBirth, timeOfBirth, latitude, longitude);
    }
}
