package com.example.b2b001.feign;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        String requestUrl = response.request().url();
        Response.Body responseBody = response.body();
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());

        if (responseStatus.is5xxServerError()) {
            return new RestApiServerException(requestUrl, responseBody);
        } else if (responseStatus.is4xxClientError()) {
            return new RestApiClientException(requestUrl, responseBody);
        } else {
            return new Exception("Generic exception");
        }
    }
}
