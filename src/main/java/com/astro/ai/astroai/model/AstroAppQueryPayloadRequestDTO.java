package com.astro.ai.astroai.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class AstroAppQueryPayloadRequestDTO {

    @JsonProperty("query")
    private AstroAppQueryRequestDTO query;

    public AstroAppQueryPayloadRequestDTO() {
    }

    public AstroAppQueryRequestDTO getQuery() {
        return query;
    }

    public void setQuery(AstroAppQueryRequestDTO query) {
        this.query = query;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AstroAppQueryPayloadRequestDTO that = (AstroAppQueryPayloadRequestDTO) o;
        return Objects.equals(query, that.query);
    }

    @Override
    public int hashCode() {
        return Objects.hash(query);
    }

    @Override
    public String toString() {
        return "AstroAppQueryPayloadRequestDTO{" +
                "query=" + query +
                '}';
    }
}
