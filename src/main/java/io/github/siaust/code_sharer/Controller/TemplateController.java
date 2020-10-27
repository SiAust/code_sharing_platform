package io.github.siaust.code_sharer.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/code")
    public String getCode(Model model) {
        model.addAttribute("code", "public static void main(String... args) {}");
        return "code";
    }
}
