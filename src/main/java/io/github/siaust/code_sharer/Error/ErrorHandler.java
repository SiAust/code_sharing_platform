package io.github.siaust.code_sharer.Error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = ItemNotFoundException.class)
    public ResponseEntity<Object> itemNotFound(ItemNotFoundException itemNotFoundException) {
        return new ResponseEntity<>(itemNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }


}
