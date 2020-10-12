package com.logmein.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
/**
 * This is global exception handler for all the rest controllers.
 * @author Guru
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	/**
	 * This method will handle ResourceNotFoundException type exception.
	 * @param ResourceNotFoundException 
	 * @return ResponseEntityBuilder
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException ex) {
       
        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());
        
        ApiError err = new ApiError(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST, 
            "Resource Not Found" ,
            details);
        
        return ResponseEntityBuilder.build(err);
    }
	/**
	 * This method will handle invalid argument errors 
	 * @param MethodArgumentTypeMismatchException 
	 * @param request
	 * @return ResponseEntityBuilder
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(
        MethodArgumentTypeMismatchException ex,
        WebRequest request) {
        
        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());
      
        ApiError err = new ApiError(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST, 
            "Type Mismatch" ,
            details);
        
        return ResponseEntityBuilder.build(err);
    }
}