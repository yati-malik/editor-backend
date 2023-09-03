package com.intuit.editor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    private String code;
    private String errorMessage;

    public Error() {
    }

    public Error(String errorCode, String errorMessage) {
        this.code = errorCode;
        this.errorMessage = errorMessage;
    }
}
