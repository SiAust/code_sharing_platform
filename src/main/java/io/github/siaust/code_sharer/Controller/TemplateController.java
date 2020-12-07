package io.github.siaust.code_sharer.Controller;

import io.github.siaust.code_sharer.Repository.SnippetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TemplateController {
    @Autowired
    SnippetRepository snippetRepository;

    @GetMapping("/code/{n}")
    public String getCode(@PathVariable long n, Model model) {
        if (snippetRepository.findById(n).isPresent()) {
            model.addAttribute("snippet", snippetRepository.findById(n).get());
        } else {
            model.addAttribute("error", true);
        }
        return "code";
    }

    @GetMapping("/code/latest")
    public String getCodeLatest(Model model) {
        model.addAttribute("snippets", snippetRepository.findTop10ByOrderByIdDesc());
        return "latest";
    }

    @GetMapping("/code/new")
    public String getCodeNew(Model model) {
        return "new";
    }

}
