package com.astro.ai.astroai.standards.restManger;

import com.astro.ai.astroai.standards.restManger.responses.CustomResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

public interface IRestManager {

    <T> CustomResponse get(String url, MultiValueMap<String, String> params, Class<T> responseClass);

    <T> CustomResponse post(String baseUrl, MultiValueMap<String, String> params, MultiValueMap<String, String> headers, Object body, Class<T> responseClass);

}
