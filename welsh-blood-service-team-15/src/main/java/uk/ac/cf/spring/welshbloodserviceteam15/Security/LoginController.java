package uk.ac.cf.spring.welshbloodserviceteam15.Security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {



    @GetMapping("/Login/login")
    public String login() {
        return "/Login/login";
    }

}

