package io.github.siaust.code_sharer.Controller;

import io.github.siaust.code_sharer.Model.Snippet;
import io.github.siaust.code_sharer.Repository.SnippetRepository;
import io.github.siaust.code_sharer.Service.SnippetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class ApiController {

    @Autowired
    SnippetService snippetService;

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    @GetMapping("/api/code/{uuid}")
    public Snippet getCodeJSON(@PathVariable UUID uuid) {
        log.info("GET Request for Snippet ID= {} from DB", uuid);
        return snippetService.getSnippet(uuid); // todo handle exceptions
    }

    @GetMapping("/api/code/latest")
    public List<Snippet> getCodeJSON() {
        log.info("API GET for latest Snippets from DB");
        return snippetService.getLatest();
    }

    @PostMapping("/api/code/new")
    public Map<String, String> postNewSnippet(@RequestBody Snippet snippet) {
        log.info("API POST new Snippet to DB");
        return  snippetService.saveSnippet(snippet);
    }

}
