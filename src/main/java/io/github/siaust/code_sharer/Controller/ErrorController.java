package io.github.siaust.code_sharer.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest httpServletRequest, Model model) {
        Object status = httpServletRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int statusCode;
        if (status != null) {
            statusCode = Integer.parseInt(status.toString());
            /*
            if (statusCode == 404) {
                // todo handle specific status
            }
            */
            model.addAttribute("error", statusCode);
        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
