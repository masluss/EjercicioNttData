package com.api.users.exceptionhandler;

import com.api.users.domains.response.Response;
import com.api.users.domains.response.ResponseEntityBuilder;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> details;
        details = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> /*error.getObjectName()+" " + */ error.getField() + ": " + error.getDefaultMessage()
                        + " [" + error.getRejectedValue() + "]")
                .collect(Collectors.toList());

        Response<Object> err = new Response<>();
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setMessage("Parámetros incorrectos");
        err.setData(details);

        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> userNotFoundException(UserNotFoundException ex){
        List<String> details= new ArrayList<>();
        details.add(ex.getMessage());

        Response<Object> err = new Response<>();
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setMessage("Datos no encontrados");
        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler(UserInternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> userInternalServerErrorException(UserInternalServerErrorException ex){
        List<String> details= new ArrayList<>();
        details.add(ex.getMessage());

        Response<Object> err = new Response<>();
        err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        err.setMessage("Mensaje de error");
        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> dataAccessException(DataAccessException ex){
        List<String> details= new ArrayList<>();
        details.add(ex.getMessage());

        Response<Object> err = new Response<>();
        err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        err.setMessage("Error interno");
        return ResponseEntityBuilder.build(err);
    }
}