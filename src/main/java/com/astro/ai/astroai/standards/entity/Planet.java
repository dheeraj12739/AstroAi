package com.astro.ai.astroai.standards.entity;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
public class Planet {

    @Id
    @GeneratedValue
    private Long planetId;
    @Column
    private String planetName;
    @Column
    private String planetFullName;
    @Column
    private String localDegree;
    @Column
    private String globalDegree;
    @Column
    private Integer rasiNumber;
    @Column
    private String zodiac;
    @Column
    private Integer houseNumber;
    @Column
    private String nakshatra;
    @Column
    private String nakshatraLord;
    @Column
    private Integer nakshatraPada;
    @Column
    private Integer nakshatraNumber;
    @Column
    private String zodiacLord;
    @Column
    private Boolean isPlanetSet;
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH }
    )
    @JoinTable(
            name = "customer_planet",
            joinColumns = @JoinColumn(name = "planetId"),
            inverseJoinColumns = @JoinColumn(name = "customerId")
    )
    private List<Customer> customers;

    public Planet() {
    }

    public Long getPlanetId() {
        return planetId;
    }

    public void setPlanetId(Long planetId) {
        this.planetId = planetId;
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public String getPlanetFullName() {
        return planetFullName;
    }

    public void setPlanetFullName(String planetFullName) {
        this.planetFullName = planetFullName;
    }

    public String getLocalDegree() {
        return localDegree;
    }

    public void setLocalDegree(String localDegree) {
        this.localDegree = localDegree;
    }

    public String getGlobalDegree() {
        return globalDegree;
    }

    public void setGlobalDegree(String globalDegree) {
        this.globalDegree = globalDegree;
    }

    public Integer getRasiNumber() {
        return rasiNumber;
    }

    public void setRasiNumber(Integer rasiNumber) {
        this.rasiNumber = rasiNumber;
    }

    public String getZodiac() {
        return zodiac;
    }

    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getNakshatra() {
        return nakshatra;
    }

    public void setNakshatra(String nakshatra) {
        this.nakshatra = nakshatra;
    }

    public String getNakshatraLord() {
        return nakshatraLord;
    }

    public void setNakshatraLord(String nakshatraLord) {
        this.nakshatraLord = nakshatraLord;
    }

    public Integer getNakshatraPada() {
        return nakshatraPada;
    }

    public void setNakshatraPada(Integer nakshatraPada) {
        this.nakshatraPada = nakshatraPada;
    }

    public Integer getNakshatraNumber() {
        return nakshatraNumber;
    }

    public void setNakshatraNumber(Integer nakshatraNumber) {
        this.nakshatraNumber = nakshatraNumber;
    }

    public String getZodiacLord() {
        return zodiacLord;
    }

    public void setZodiacLord(String zodiacLord) {
        this.zodiacLord = zodiacLord;
    }

    public Boolean getPlanetSet() {
        return isPlanetSet;
    }

    public void setPlanetSet(Boolean planetSet) {
        isPlanetSet = planetSet;
    }

    public List<Customer> getCustomer() {
        return customers;
    }

    public void setCustomer(List<Customer> customer) {
        this.customers = customer;
    }
}
