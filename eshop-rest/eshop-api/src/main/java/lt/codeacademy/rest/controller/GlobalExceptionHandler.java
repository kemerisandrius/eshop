package lt.codeacademy.rest.controller;

import lt.codeacademy.rest.services.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class, ProductNotFoundException.class})
    public ResponseEntity<ErrorContext> handleErrors(Exception ex) {
        if (ex instanceof ProductNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            return handleProductNotFoundException((ProductNotFoundException) ex, status);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleRuntimeException((RuntimeException) ex, status);
        }
    }

    private ResponseEntity<ErrorContext> handleRuntimeException(RuntimeException ex, HttpStatus status) {
        ErrorContext errorContext = new ErrorContext();
        errorContext.error = ex.getMessage();
        errorContext.code = "999";
        return new ResponseEntity<>(errorContext, status);
    }

    private ResponseEntity<ErrorContext> handleProductNotFoundException(ProductNotFoundException ex, HttpStatus status) {
        ErrorContext errorContext = new ErrorContext();
        errorContext.error = ex.getMessage();
        errorContext.code = "2";
        return new ResponseEntity<>(errorContext, status);
    }

}
