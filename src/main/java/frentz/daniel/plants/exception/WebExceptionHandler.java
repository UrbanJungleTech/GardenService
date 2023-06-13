package frentz.daniel.plants.exception;

import frentz.daniel.plants.errorResponse.ErrorResponse;
import frentz.daniel.plants.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WebExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> NotFound(NotFoundException notFoundException){
        System.out.println("not found");
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), notFoundException.getType().getSimpleName(), notFoundException.getId());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
