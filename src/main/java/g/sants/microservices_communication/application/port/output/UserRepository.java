package g.sants.microservices_communication.application.port.output;

import g.sants.microservices_communication.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,Long> {

    UserDetails findByEmail(String email);
}
