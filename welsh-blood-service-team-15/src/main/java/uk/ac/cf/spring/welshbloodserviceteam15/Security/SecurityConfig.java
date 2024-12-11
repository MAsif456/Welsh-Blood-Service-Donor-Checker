package uk.ac.cf.spring.welshbloodserviceteam15.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final DataSource dataSource;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(DataSource dataSource, PasswordEncoder passwordEncoder) {
        this.dataSource = dataSource;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> request
                        // Public access
                        .requestMatchers("/css/**", "/images/**").permitAll()
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("/", "/home", "/donor-form", "/travel-form/**", "/confirmation").permitAll()
                        // Role-specific access
                        .requestMatchers("/donor/summary/**", "/donor/summary-risks/**", "/decisionAndNotes/**", "/donor-records").hasRole("CLINICIAN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                // Login configuration
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/defaultLogin", true) // Redirect after successful login
                        .permitAll())
                // Logout configuration
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll())
                // Exception handling for access-denied
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/403"));
        return http.build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
                // Query to fetch user credentials
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                // Query to fetch user roles, removing the "ROLE_" prefix
                .authoritiesByUsernameQuery("select username, role from users where username=?");
    }
}
