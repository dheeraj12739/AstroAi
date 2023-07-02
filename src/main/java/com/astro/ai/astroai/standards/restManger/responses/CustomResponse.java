package com.astro.ai.astroai.standards.restManger.responses;

import com.astro.ai.astroai.standards.common.AbstractServiceResponse;
import org.springframework.http.HttpStatus;

public class CustomResponse<T> extends AbstractServiceResponse {

    private T body;

    private String errorBody;

    private HttpStatus httpStatus;

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getErrorBody() {
        return errorBody;
    }

    public void setErrorBody(String errorBody) {
        this.errorBody = errorBody;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String toString() {
        return "CustomResponse{" +
                "body=" + body +
                ", errorBody='" + errorBody + '\'' +
                ", httpStatus=" + httpStatus +
                '}';
    }
}
