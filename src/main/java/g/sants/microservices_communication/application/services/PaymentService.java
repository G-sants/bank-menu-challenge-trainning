package g.sants.microservices_communication.application.services;

import g.sants.microservices_communication.application.exceptions.errors.InsufficientBalanceException;
import g.sants.microservices_communication.application.exceptions.errors.UserNotFoundException;
import g.sants.microservices_communication.application.port.output.UserRepository;
import g.sants.microservices_communication.communication.Order;
import g.sants.microservices_communication.domain.Account;
import g.sants.microservices_communication.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class PaymentService {

    private final RestTemplate restTemplate;
    private final UserRepository userRepository;

    @Autowired
    public  PaymentService(RestTemplate restTemplate,
                           UserRepository userRepository){
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }

    public Order retrieveOrder(String orderId) {
        return restTemplate.getForObject("http://localhost:8081/checkout/order/" + orderId, Order.class);
    }

    public void payOrder(Long userID, Double price) {
        Optional<User> userOpt = userRepository.findById(userID);
        User user;
        if (userOpt.isPresent()) {
            user = userOpt.get();
        } else throw new UserNotFoundException();

        Account account = user.getAccount();
        if (account.timeValidation() && account.balanceCheck(price)){
            account.setBalance(account.getBalance()-price);
        }throw  new InsufficientBalanceException();
    }
}
