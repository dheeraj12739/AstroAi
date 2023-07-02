package com.astro.ai.astroai.standards.restManger;

import com.astro.ai.astroai.standards.common.GenericResponseDTO;
import com.astro.ai.astroai.standards.restManger.responses.CustomResponse;
import com.astro.ai.astroai.standards.restManger.responses.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class RestTemplateManager implements IRestManager{


    @Autowired
    private RestTemplate restTemplate;

    public RestTemplateManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <T> CustomResponse get(String url, MultiValueMap<String, String> params, Class<T> responseClass) {

        RestResponse responseToBeReturn;
        try {

            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(url).queryParams(params);
            RequestEntity requestEntity = new RequestEntity<>(HttpMethod.GET, URI.create(uriComponentsBuilder.toUriString()));

            ResponseEntity<T> response = restTemplate.exchange(requestEntity, responseClass);
            responseToBeReturn = createResponseTobeReturn(response);
        }catch (RestClientException restClientException) {

            responseToBeReturn = createResponseTobeReturn(restClientException);
        }catch (Exception e) {

            responseToBeReturn = createResponseTobeReturn(e);
        }

        return responseToBeReturn;
    }

    @Override
    public <T> CustomResponse post(String baseUrl, MultiValueMap<String, String> params, MultiValueMap<String, String> headers, Object body, Class<T> responseClass) {
        RestResponse returnResponse;
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl).queryParams(params);
            RequestEntity requestEntity = new RequestEntity(body, headers, HttpMethod.POST, URI.create(builder.toUriString()));
            ResponseEntity<T> response = restTemplate.exchange(requestEntity, responseClass);
            returnResponse = createResponseTobeReturn(response);
        } catch (RestClientException e) {
            returnResponse = createResponseTobeReturn(e);
        }catch( Exception e){
            returnResponse = createResponseTobeReturn(e);
        }
        return returnResponse;
    }

    private RestResponse createResponseTobeReturn(Exception e) {

        RestResponse returnResponse = new RestResponse();
        returnResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        String eMessage = "internal server error : "+ e.getMessage();
        return returnResponse;
    }

    private RestResponse createResponseTobeReturn(RestClientException restClientException) {

        RestResponse returnResponse = new RestResponse();
        returnResponse.setHttpStatus(HttpStatus.SERVICE_UNAVAILABLE);
        String eMessage = "Server respond with message : "+ restClientException.getMessage();
        GenericResponseDTO genericResponseDTO = new GenericResponseDTO(HttpStatus.SERVICE_UNAVAILABLE, eMessage, eMessage);
        returnResponse.setBody(genericResponseDTO);
        return returnResponse;
    }

    private <T> RestResponse createResponseTobeReturn(ResponseEntity<T> response) {

        RestResponse restResponse = new RestResponse();
        restResponse.setHeaders(response.getHeaders());
        restResponse.setBody(response.getBody());
        restResponse.setHttpStatus(response.getStatusCode());

        return restResponse;
    }
}
