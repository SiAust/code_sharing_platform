package io.github.siaust.code_sharer.Controller;

import io.github.siaust.code_sharer.Model.Snippet;
import io.github.siaust.code_sharer.Service.SnippetService;
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

    @GetMapping("/code/{uuid}")
    public String getCode(@PathVariable UUID uuid, Model model) {
        Snippet snippet;
        if ((snippet = snippetService.getSnippet(uuid)) != null) {
            model.addAttribute("snippet", snippet);
            model.addAttribute("secret", snippet.isSecret());
        } else {
            model.addAttribute("error", true);
        }
        return "code";
    }

    @GetMapping("/code/latest")
    public String getCodeLatest(Model model) {
        model.addAttribute("snippets", snippetService.getLatest());
        return "latest";
    }

    @GetMapping("/code/new")
    public String getCodeNew(Model model) {
        return "new";
    }

}
