package in.nic.smart_contact_manager.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
