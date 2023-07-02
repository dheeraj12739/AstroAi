package com.astro.ai.astroai.standards.restManger.responses;


import org.springframework.http.HttpHeaders;

public class RestResponse extends CustomResponse{

    private HttpHeaders headers;

    public RestResponse() {
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    @Override
    public String toString() {
        return "RestResponse{" +
                "headers=" + headers +
                '}';
    }
}
