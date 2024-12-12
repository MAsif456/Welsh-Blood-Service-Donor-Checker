package uk.ac.cf.spring.welshbloodserviceteam15.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;


@Configuration
public class ExternalConfig {
    // @Value used to access the application.properties files and get
    // the values of the variables.
    @Value("${DB_USERNAME}")
    private String dbUsername;

    @Value("${DB_PASSWORD}")
    private String dbPassword;

    @Value("${DB_URL}")
    private String dbUrl;


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // Set and trim the values of the dataSource before returning it,
        // preventing whitespace issues.
        dataSource.setUrl(dbUrl.trim());
        dataSource.setUsername(dbUsername.trim());
        dataSource.setPassword(dbPassword.trim());
        return dataSource;
    }
}