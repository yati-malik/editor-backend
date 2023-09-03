package com.intuit.editor.exception;

public enum ContentErrors {
    NOT_FOUND("CE001","Content not found"),
    CONTENT_UPDATION_FAILED("CE002","Something went wrong, unable to update the content"),
    SOMETHING_WENT_WRONG ("CE003", "Something went wrong");
    private final String code;

    private final String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ContentErrors(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
