package uk.ac.cf.spring.welshbloodserviceteam15.Errors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/403")
    public String error403() {
        System.out.println("403 error");
        return "Error/403";
    }

}