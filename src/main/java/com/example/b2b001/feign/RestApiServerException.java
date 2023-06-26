package com.example.b2b001.feign;

import feign.Response;

public class RestApiServerException extends Exception{
    private String requestUrl;
    private Response.Body responseBody;

    public RestApiServerException( String requestUrl, Response.Body responseBody) {
        this.requestUrl = requestUrl;
        this.responseBody = responseBody;
    }
}
