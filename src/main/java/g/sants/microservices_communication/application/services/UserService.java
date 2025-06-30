package g.sants.microservices_communication.application.services;

import g.sants.microservices_communication.application.port.output.UserRepository;
import g.sants.microservices_communication.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void createNewAccount(Long customerID, String password, String name, String lastName,
                                 String email, String accType) {
        User user = new User(customerID,password ,name, lastName, email, accType);
        userRepository.save(user);
    }
}
