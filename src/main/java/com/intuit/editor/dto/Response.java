package com.intuit.editor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private boolean success;
    private T body;
    private List<Error> errors;

    private Response() {
    }

    public static <T> Response<T> successResponse(T body) {
        Response<T> response = new Response<>();
        response.success = true;
        response.body = body;
        return response;
    }

    public static Response<Object> successResponse() {
        Response<Object> response = new Response<>();
        response.success = true;
        return response;
    }

    public static <T> Response<T> failureResponse(List<Error> errors) {
        Response<T> response = new Response<>();
        response.success = false;
        response.errors = errors;
        return response;
    }

    public static <T> Response<T> failureResponseWithBody(T body) {
        Response<T> response = new Response<>();
        response.success = false;
        response.body = body;
        return response;
    }

}
