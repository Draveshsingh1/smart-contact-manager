package in.nic.smart_contact_manager.exceptions;

import io.jsonwebtoken.JwtException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.sasl.AuthenticationException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String,String>> handleConstraintViolation(ConstraintViolationException constraintViolationException){
       Map<String, String> error = new LinkedHashMap<>();
       Set<ConstraintViolation<?>> constraintViolations = constraintViolationException.getConstraintViolations();

       constraintViolations.forEach(constraintViolation -> {
           String propertyName = constraintViolation.getPropertyPath().toString();
           String message = constraintViolation.getMessage();
           error.put(propertyName,message);
       });
       return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String,String >>handleAuthenticationException(AuthenticationException exception){
        Map<String,String > response = new HashMap<>();
        response.put("error","Authentication Failed");
        response.put("message",exception.getMessage());
        return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Map<String,String >>handleJwtException(JwtException exception){
        Map<String,String > response = new HashMap<>();
        response.put("error","Invalid JWT Token");
        response.put("message",exception.getMessage());
        return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String >>handleGenericException(Exception exception){
        Map<String,String > response = new HashMap<>();
        response.put("error","Internal Server Error");
        response.put("message",exception.getMessage());
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
