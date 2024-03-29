package ado.rush.university.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomException> handleCustomException(ResourceNotFoundException ex ){

    List<String> details = new ArrayList<String>();
    details.add(ex.getMessage());

    CustomException exception = new CustomException();
    exception.setStatus(HttpStatus.BAD_REQUEST);
    exception.setMessage(ex.getMessage());
    exception.setErrors(details);
      return ResponseEntity.badRequest().body(exception);
  }

}
