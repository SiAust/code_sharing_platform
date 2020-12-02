package io.github.siaust.code_sharer.Controller;

import io.github.siaust.code_sharer.Model.Snippet;
import io.github.siaust.code_sharer.Repository.TempRepo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class TemplateController {

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/code")
    public String getCode(Model model) {
        model.addAttribute("snippet" , TempRepo.getSnippet() == null ? new Snippet("no code") : TempRepo.getSnippet());

        return "code";
    }

    @GetMapping("/code/new")
    public String getCodeNew(Model model) {

        return "new";
    }

}
