package g.sants.microservices_communication.application.port.output;

import g.sants.microservices_communication.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
