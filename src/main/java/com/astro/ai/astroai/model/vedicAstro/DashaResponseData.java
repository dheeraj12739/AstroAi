package com.astro.ai.astroai.model.vedicAstro;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class DashaResponseData {

    @JsonProperty("antardashas")
    private List<List<String>> antarDashas;

    @JsonProperty("antardasha_order")
    private List<List<String>> antarDashaOrder;

    public DashaResponseData() {
    }

    public List<List<String>> getAntarDashas() {
        return antarDashas;
    }

    public void setAntarDashas(List<List<String>> antarDashas) {
        this.antarDashas = antarDashas;
    }

    public List<List<String>> getAntarDashaOrder() {
        return antarDashaOrder;
    }

    public void setAntarDashaOrder(List<List<String>> antarDashaOrder) {
        this.antarDashaOrder = antarDashaOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DashaResponseData that = (DashaResponseData) o;
        return Objects.equals(antarDashas, that.antarDashas) && Objects.equals(antarDashaOrder, that.antarDashaOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(antarDashas, antarDashaOrder);
    }

    @Override
    public String toString() {
        return "DashaResponseData{" +
                "antarDashas=" + antarDashas +
                ", antarDashaOrder=" + antarDashaOrder +
                '}';
    }
}
