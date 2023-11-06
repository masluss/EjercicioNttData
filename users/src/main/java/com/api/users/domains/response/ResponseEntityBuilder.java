package com.api.users.domains.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {
    public static ResponseEntity<Object> build(Response<Object> err) {
        return new ResponseEntity<>(err, HttpStatus.valueOf(err.getStatus()));
    }
}