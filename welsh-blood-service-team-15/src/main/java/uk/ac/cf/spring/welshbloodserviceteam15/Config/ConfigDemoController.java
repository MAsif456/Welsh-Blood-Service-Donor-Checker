package uk.ac.cf.spring.welshbloodserviceteam15.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Controller used to debug the application property values

@RestController
public class ConfigDemoController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${DB_USERNAME}")
    private String username;

    @Value("${DB_PASSWORD}")
    private String password;

    @GetMapping("/config-test")
    public String getApplicationName() {
        return "Application Name: " + applicationName + "\nUsername: "
                + username + "\nPassword: " + password;
    }
}
