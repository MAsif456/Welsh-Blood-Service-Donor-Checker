package uk.ac.cf.spring.welshbloodserviceteam15.Security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {



    @GetMapping("/login")
    public String login() {
        return "/Login/login";
    }

    @GetMapping("/defaultLogin")
    public String defaultAfterLogin(Authentication authentication) {

        // Check if the authenticated user has the ROLE_CLINICIAN role
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CLINICIAN"))) {
            return "redirect:/donor-records"; // Redirect to donor records for clinicians
        }
        // Check if the authenticated user has the ROLE_ADMIN role
        else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/home"; // Redirect to home for admins
        }

        // Default fallback if no recognized roles are found
        return "redirect:/home";
    }
}

