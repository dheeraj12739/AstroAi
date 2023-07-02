package com.astro.ai.astroai.standards.entity;

import javax.persistence.*;

@Table
@Entity
public class PlanetDetailsCredentials {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String apiKey;

    @Column
    private Boolean active;

    public PlanetDetailsCredentials() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "PlanetDetailsCredentials{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", isActive=" + active +
                '}';
    }
}
