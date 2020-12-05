package io.github.siaust.code_sharer.Controller;

import io.github.siaust.code_sharer.Model.Snippet;
import io.github.siaust.code_sharer.Repository.TempRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ApiController {

    @Autowired
    TempRepo tempRepo;

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    @GetMapping("/api/code/{n}")
    public Snippet getCodeJSON(@PathVariable int n) {
        System.out.println("Here we are");
        return tempRepo.getSnippet(n).isEmpty() ? new Snippet("no snippets") : tempRepo.getSnippet(n).get();
    }

    @GetMapping("/api/code/latest")
    public List<Snippet> getCodeJSON() {
//        tempRepo.getAllSnippets().forEach(System.out::println);
        List<Snippet> temp = new ArrayList<>(tempRepo.getAllSnippets());
        Collections.reverse(temp);
        return temp.stream().limit(10).collect(Collectors.toList());
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<String> postNewSnippet(@RequestBody Snippet snippet) {
        tempRepo.addSnippet(snippet);
        return new ResponseEntity<>("{id: \"" + tempRepo.getID(snippet) + "\"}", HttpStatus.OK);
    }

}
