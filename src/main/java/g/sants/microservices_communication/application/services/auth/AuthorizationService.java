package g.sants.microservices_communication.application.services.auth;

import g.sants.microservices_communication.application.dto.RegisterDTORequest;
import g.sants.microservices_communication.application.exceptions.errors.RegistrationAlreadyDoneException;
import g.sants.microservices_communication.application.port.output.UserRepository;
import g.sants.microservices_communication.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public void registerNewUser(RegisterDTORequest data){
        if(this.userRepository.findByEmail(data.email()) != null)
            throw new RegistrationAlreadyDoneException();

        String encrytedPass = new BCryptPasswordEncoder().encode(data.password());

        User newUser = new User(data.customerID(), encrytedPass, data.name(),
                data.lastName(),data.lastName(),data.accType());

        this.userRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findByEmail(email);
        if (userDetails == null) {
            throw new UsernameNotFoundException("User not Found");
        }
        return userDetails;
    }
}
