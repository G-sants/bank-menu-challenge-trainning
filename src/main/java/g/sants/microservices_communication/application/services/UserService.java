package g.sants.microservices_communication.application.services;

import g.sants.microservices_communication.application.dto.LoginDTORequest;
import g.sants.microservices_communication.application.dto.RegisterDTORequest;
import g.sants.microservices_communication.application.exceptions.errors.UserNotFoundException;
import g.sants.microservices_communication.application.port.output.UserRepository;
import g.sants.microservices_communication.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService( UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public User getUser(Long userID) {
        Optional<User> retUser = userRepository.findById(userID);
        if(retUser.isPresent()) {
            return retUser.get();
        }
        throw  new UserNotFoundException();
    }
}
