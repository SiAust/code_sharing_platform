package io.github.siaust.code_sharer.Controller;

import io.github.siaust.code_sharer.Model.Snippet;
import io.github.siaust.code_sharer.Repository.TempRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    @GetMapping("/api/code")
    public Snippet getCodeJSON() {
        System.out.println("Here we are");
        return TempRepo.getSnippet() == null ? new Snippet("no code") : TempRepo.getSnippet();
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<String> postNewSnippet(@RequestBody Snippet snippet) {
        TempRepo.setSnippet(snippet);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

}
