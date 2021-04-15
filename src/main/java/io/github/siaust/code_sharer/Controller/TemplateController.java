package io.github.siaust.code_sharer.Controller;

import io.github.siaust.code_sharer.Error.ItemNotFoundException;
import io.github.siaust.code_sharer.Error.SnippetNotFound;
import io.github.siaust.code_sharer.Model.Snippet;
import io.github.siaust.code_sharer.Service.SnippetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
public class TemplateController {
    @Autowired
    SnippetService snippetService;

    private static final Logger log = LoggerFactory.getLogger(TemplateController.class);

    @GetMapping("/code/{uuid}")
    public String getCode(@PathVariable UUID uuid, Model model) {
        log.info("GET code for Snippet UUID {}", uuid);
        Snippet snippet;
        if ((snippet = snippetService.getSnippet(uuid)) != null) {
            model.addAttribute("snippet", snippet);
            model.addAttribute("secret", snippet.isSecret());
        } else {
            throw new SnippetNotFound();
        }
        model.addAttribute("error", false);
        return "code";
    }

    @GetMapping("/code/latest")
    public String getCodeLatest(Model model) {
        log.info("GET latest snippets");
        model.addAttribute("snippets", snippetService.getLatest());
        return "latest";
    }

    @GetMapping("/code/new")
    public String getCodeNew() {
        log.info("GET new template");
        return "new";
    }

}
