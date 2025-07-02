package g.sants.microservices_communication.application.services;

import g.sants.microservices_communication.application.dto.LoginDTORequest;
import g.sants.microservices_communication.application.dto.RegisterDTORequest;
import g.sants.microservices_communication.application.port.output.UserRepository;
import g.sants.microservices_communication.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthService( UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public void registerNewUser(RegisterDTORequest data){
        User user = new User(data.customerID(),data.password(),data.name(),
                data.lastName(),data.email(), data.accType());
        userRepository.save(user);
    }

    public String loginUser(LoginDTORequest data){
        User user = userRepository.findByEmail(data.email());
        if(user != null){
            return "ok";
        }
        return null;
    }

}
