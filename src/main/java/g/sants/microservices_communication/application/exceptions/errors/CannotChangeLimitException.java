package g.sants.microservices_communication.application.exceptions.errors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CannotChangeLimitException extends UsernameNotFoundException{

    public CannotChangeLimitException() {
        super("Unable to Change Limit Right Now");
    }

    public CannotChangeLimitException(String msg) {super(msg);}
}
