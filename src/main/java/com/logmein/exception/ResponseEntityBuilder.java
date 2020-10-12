package com.logmein.exception;

import  org.springframework.http.ResponseEntity;
/**
 * This class builds the response entity
 * @author Guru
 *
 */
public class ResponseEntityBuilder {
    public static ResponseEntity<Object> build(ApiError apiError) {
          return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
