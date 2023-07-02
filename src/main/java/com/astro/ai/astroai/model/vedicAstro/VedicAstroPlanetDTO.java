package com.astro.ai.astroai.model.vedicAstro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VedicAstroPlanetDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("full_name")
    private String full_name;

    @JsonProperty("local_degree")
    private double local_degree;

    @JsonProperty("global_degree")
    private double global_degree;

    @JsonProperty("rasi_no")
    private int rasiNo;

    @JsonProperty("zodiac")
    private String zodiac;

    @JsonProperty("house")
    private int house;

    @JsonProperty("nakshatra")
    private String nakshatra;

    @JsonProperty("nakshatra_lord")
    private String nakshatraLord;

    @JsonProperty("nakshatra_pada")
    private int nakshatraPada;

    @JsonProperty("nakshatra_no")
    private int nakshatraNo;

    @JsonProperty("zodiac_lord")
    private String zodiacLord;

    @JsonProperty("is_planet_set")
    private boolean isPlanetSet;

    @JsonProperty("retro")
    private boolean retro;

    @JsonProperty("basic_avastha")
    private String basicAvastha;

    @JsonProperty("speed_radians_per_day")
    private double speedRadiansPerDay;

    public VedicAstroPlanetDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public double getLocal_degree() {
        return local_degree;
    }

    public void setLocal_degree(double local_degree) {
        this.local_degree = local_degree;
    }

    public double getGlobal_degree() {
        return global_degree;
    }

    public void setGlobal_degree(double global_degree) {
        this.global_degree = global_degree;
    }

    public int getRasiNo() {
        return rasiNo;
    }

    public void setRasiNo(int rasiNo) {
        this.rasiNo = rasiNo;
    }

    public String getZodiac() {
        return zodiac;
    }

    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
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

    public int getNakshatraPada() {
        return nakshatraPada;
    }

    public void setNakshatraPada(int nakshatraPada) {
        this.nakshatraPada = nakshatraPada;
    }

    public int getNakshatraNo() {
        return nakshatraNo;
    }

    public void setNakshatraNo(int nakshatraNo) {
        this.nakshatraNo = nakshatraNo;
    }

    public String getZodiacLord() {
        return zodiacLord;
    }

    public void setZodiacLord(String zodiacLord) {
        this.zodiacLord = zodiacLord;
    }

    public boolean isPlanetSet() {
        return isPlanetSet;
    }

    public void setPlanetSet(boolean planetSet) {
        isPlanetSet = planetSet;
    }

    public boolean isRetro() {
        return retro;
    }

    public void setRetro(boolean retro) {
        this.retro = retro;
    }

    public String getBasicAvastha() {
        return basicAvastha;
    }

    public void setBasicAvastha(String basicAvastha) {
        this.basicAvastha = basicAvastha;
    }

    public double getSpeedRadiansPerDay() {
        return speedRadiansPerDay;
    }

    public void setSpeedRadiansPerDay(double speedRadiansPerDay) {
        this.speedRadiansPerDay = speedRadiansPerDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VedicAstroPlanetDTO that = (VedicAstroPlanetDTO) o;
        return Double.compare(that.local_degree, local_degree) == 0 && Double.compare(that.global_degree, global_degree) == 0 && rasiNo == that.rasiNo && house == that.house && nakshatraPada == that.nakshatraPada && nakshatraNo == that.nakshatraNo && isPlanetSet == that.isPlanetSet && retro == that.retro && Double.compare(that.speedRadiansPerDay, speedRadiansPerDay) == 0 && Objects.equals(name, that.name) && Objects.equals(full_name, that.full_name) && Objects.equals(zodiac, that.zodiac) && Objects.equals(nakshatra, that.nakshatra) && Objects.equals(nakshatraLord, that.nakshatraLord) && Objects.equals(zodiacLord, that.zodiacLord) && Objects.equals(basicAvastha, that.basicAvastha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, full_name, local_degree, global_degree, rasiNo, zodiac, house, nakshatra, nakshatraLord, nakshatraPada, nakshatraNo, zodiacLord, isPlanetSet, retro, basicAvastha, speedRadiansPerDay);
    }

    @Override
    public String toString() {
        return "VedicAstroPlanetDTO{" +
                "name='" + name + '\'' +
                ", full_name='" + full_name + '\'' +
                ", local_degree=" + local_degree +
                ", global_degree=" + global_degree +
                ", rasiNo=" + rasiNo +
                ", zodiac='" + zodiac + '\'' +
                ", house=" + house +
                ", nakshatra='" + nakshatra + '\'' +
                ", nakshatraLord='" + nakshatraLord + '\'' +
                ", nakshatraPada=" + nakshatraPada +
                ", nakshatraNo=" + nakshatraNo +
                ", zodiacLord='" + zodiacLord + '\'' +
                ", isPlanetSet=" + isPlanetSet +
                ", retro=" + retro +
                ", basicAvastha='" + basicAvastha + '\'' +
                ", speedRadiansPerDay=" + speedRadiansPerDay +
                '}';
    }
}


