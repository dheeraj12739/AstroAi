package com.astro.ai.astroai.model;

import com.astro.ai.astroai.standards.entity.Dasha;
import com.astro.ai.astroai.standards.entity.Planet;

import java.util.List;
import java.util.Objects;

public class AstroAppPlanetAndDashaDTO {

    private List<Planet> planets;

    private List<Dasha> dashas;

    public AstroAppPlanetAndDashaDTO() {
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }

    public List<Dasha> getDashas() {
        return dashas;
    }

    public void setDashas(List<Dasha> dashas) {
        this.dashas = dashas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AstroAppPlanetAndDashaDTO that = (AstroAppPlanetAndDashaDTO) o;
        return Objects.equals(planets, that.planets) && Objects.equals(dashas, that.dashas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planets, dashas);
    }

    @Override
    public String toString() {
        return "AstroAppPlanetAndDashaDTO{" +
                "planets=" + planets +
                ", dashas=" + dashas +
                '}';
    }
}
