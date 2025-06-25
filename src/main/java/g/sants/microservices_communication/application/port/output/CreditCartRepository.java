package g.sants.microservices_communication.application.port.output;

import g.sants.microservices_communication.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCartRepository extends JpaRepository<CreditCard,Long> {
}
