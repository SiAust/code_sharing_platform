package io.github.siaust.code_sharer.Error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = ItemNotFoundException.class)
    public ResponseEntity<Object> itemNotFound(ItemNotFoundException itemNotFoundException) {
        return new ResponseEntity<>(itemNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = SnippetNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String snippetNotFoundTemplateView(Model model) {
        model.addAttribute("error", true);
        return "code";
    }


}
