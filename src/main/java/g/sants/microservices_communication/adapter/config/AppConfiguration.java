package g.sants.microservices_communication.adapter.config;

import g.sants.microservices_communication.domain.Account;
import g.sants.microservices_communication.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {

    @Bean
    public Account account() {
        return new Account();
    }

    @Bean
    public User user() {
        return new User();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
