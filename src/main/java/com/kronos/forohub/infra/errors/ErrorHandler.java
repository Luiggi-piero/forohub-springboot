package com.kronos.forohub.infra.errors;

import com.kronos.forohub.validations.ValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // actua como un proxy, para interceptar los metodos del controlador y capturar las excepciones
public class ErrorHandler {

    // cuando se lanza la excepcion  EntityNotFoundException  se ejecutará este metodo
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400(MethodArgumentNotValidException e){ // el parametro obtiene las excepciones
        var errors = e.getFieldErrors().stream().map(DataErrorValidation::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    private record DataErrorValidation(String field, String error){
        public DataErrorValidation(FieldError fieldError){
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity handlerErrors(ValidationException e){ // el param obtiene las excepciones
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
