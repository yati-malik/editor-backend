package com.intuit.editor.exception;

import com.intuit.editor.exception.ApplicationException;
import com.intuit.editor.exception.ContentErrors;

public class ContentExceptions extends ApplicationException {
    public ContentExceptions(String code, String message) {
        super(code, message);
    }

    public ContentExceptions(ContentErrors errorEnum) {
        super(errorEnum.getCode(), errorEnum.getMessage());
    }
}
