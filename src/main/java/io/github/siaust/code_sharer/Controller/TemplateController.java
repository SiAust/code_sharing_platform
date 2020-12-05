package io.github.siaust.code_sharer.Controller;

import io.github.siaust.code_sharer.Model.Snippet;
import io.github.siaust.code_sharer.Repository.TempRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TemplateController {
    @Autowired
    TempRepo tempRepo;

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/code/{n}")
    public String getCode(@PathVariable int n, Model model) {
        model.addAttribute("snippets" ,
                tempRepo.getSnippet(n).isEmpty()
                        ? List.of(new Snippet("no code snippet"))
                        : List.of(tempRepo.getSnippet(n).get()));
        return "code";
    }

    @GetMapping("/code/latest")
    public String getCodeLatest(Model model) {
        List<Snippet> temp = new ArrayList<>(tempRepo.getAllSnippets());
        Collections.reverse(temp);
        model.addAttribute("snippets", temp.stream().limit(10).collect(Collectors.toList()));
        return "latest";
    }

    @GetMapping("/code/new")
    public String getCodeNew(Model model) {
        return "new";
    }

}
