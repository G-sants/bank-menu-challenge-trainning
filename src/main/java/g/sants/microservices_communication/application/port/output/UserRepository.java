package g.sants.microservices_communication.application.port.output;

import g.sants.microservices_communication.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
