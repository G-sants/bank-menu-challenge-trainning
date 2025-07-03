package g.sants.microservices_communication.application.exceptions.errors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class InsufficientBalanceException extends UsernameNotFoundException{

    public InsufficientBalanceException() {
        super("Your balance is too Low for this Purchase");
    }

    public InsufficientBalanceException(String msg) {super(msg);}
}
