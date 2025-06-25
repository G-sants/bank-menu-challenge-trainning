package g.sants.microservices_communication.adapter;

import g.sants.microservices_communication.domain.Account;
import g.sants.microservices_communication.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
