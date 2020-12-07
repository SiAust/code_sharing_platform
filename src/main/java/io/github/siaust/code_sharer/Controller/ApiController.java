package io.github.siaust.code_sharer.Controller;

import io.github.siaust.code_sharer.Model.Snippet;
import io.github.siaust.code_sharer.Repository.SnippetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ApiController {

    @Autowired
    SnippetRepository snippetRepository;

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    @GetMapping("/api/code/{n}")
    public Snippet getCodeJSON(@PathVariable long n) {
        log.info("Request for Snippet {} from DB", n);
        return snippetRepository.findById(n).get(); // todo handle exceptions
    }

    @GetMapping("/api/code/latest")
    public List<Snippet> getCodeJSON() {
        log.info("Request for latest Snippets from DB");
        return snippetRepository.findTop10ByOrderByIdDesc();
    }

    @PostMapping("/api/code/new")
    public Map<String, String> postNewSnippet(@RequestBody Snippet snippet) {
        log.info("Post new Snippet to DB");
        return  Map.of("id", String.valueOf(snippetRepository.save(snippet).getId()));
    }

}
