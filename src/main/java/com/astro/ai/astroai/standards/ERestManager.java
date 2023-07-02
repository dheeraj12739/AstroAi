package com.astro.ai.astroai.standards;

public enum ERestManager {

    UNIREST("UNIREST"),
    REST_TEMPLATE("REST_TEMPLATE");

    private String code;

    ERestManager (String code) {
        this.code = code;
    }

    public String getCode () {
        return code;
    }
}
