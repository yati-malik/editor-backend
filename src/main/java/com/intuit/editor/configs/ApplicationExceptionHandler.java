package com.intuit.editor.configs;

import com.intuit.editor.dto.Error;
import com.intuit.editor.exception.ApplicationException;
import com.intuit.editor.dto.Response;
import com.intuit.editor.exception.ContentErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@ControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Response<Object>> handleException(Exception exception) {
        return new ResponseEntity<>(Response.failureResponseWithBody(new Error(ContentErrors.SOMETHING_WENT_WRONG.getCode(), ContentErrors.SOMETHING_WENT_WRONG.getMessage())), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ResponseBody
    @ExceptionHandler(value = {ApplicationException.class})
    public ResponseEntity<Response<Object>> handleException(ApplicationException exception) {
        HttpStatus httpStatus = exception.getHttpStatus() == null ? HttpStatus.BAD_REQUEST : exception.getHttpStatus();
        return new ResponseEntity<>(Response.failureResponseWithBody(new Error(exception.getCode(), exception.getMessage())), httpStatus);
    }
}
