package com.astro.ai.astroai.standards.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long customerId;
    @Column
    private String dateOfBirth;
    @Column
    private String longitude;
    @Column
    private String latitude;
    @Column
    private String timeOfBirth;
    @Column
    private String astroAppCustomerId;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH },
            mappedBy = "customers"
    )
    private List<Planet> planets;

    public Customer() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getTimeOfBirth() {
        return timeOfBirth;
    }

    public void setTimeOfBirth(String timeOfBirth) {
        this.timeOfBirth = timeOfBirth;
    }

    public String getAstroAppCustomerId() {
        return astroAppCustomerId;
    }

    public void setAstroAppCustomerId(String astroAppCustomerId) {
        this.astroAppCustomerId = astroAppCustomerId;
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }
}
