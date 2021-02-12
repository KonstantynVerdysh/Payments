package ua.com.verdysh.payments.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.com.verdysh.payments.domain.dto.ExceptionDTO;
import ua.com.verdysh.payments.exceptions.ValidationException;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDTO handle(ValidationException ex){
        return new ExceptionDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDTO handle(EntityNotFoundException ex){
        return new ExceptionDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDTO handle(Exception ex) {
        if (ex instanceof NullPointerException) {
            return new ExceptionDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        }
        return new ExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }
}
