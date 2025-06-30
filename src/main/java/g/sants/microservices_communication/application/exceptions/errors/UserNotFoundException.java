package g.sants.microservices_communication.application.exceptions.errors;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Username not Found");
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
