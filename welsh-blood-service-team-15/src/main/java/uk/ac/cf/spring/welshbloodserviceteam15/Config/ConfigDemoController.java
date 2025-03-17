package uk.ac.cf.spring.welshbloodserviceteam15.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Controller used to debug the application property values

@RestController
public class ConfigDemoController {

    @Value("${APPLICATION_NAME:welsh-blood-service-team-15}")
    private String applicationName;

    @Value("${DB_USERNAME:root}")
    private String username;

    @Value("${DB_PASSWORD:comsc}")
    private String password;

    @GetMapping("/config-test")
    public String getApplicationName() {
        return "Application Name: " + applicationName + "\nUsername: "
                + username + "\nPassword: " + password;
    }
}
