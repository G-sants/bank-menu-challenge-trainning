package g.sants.microservices_communication.application.exceptions.errors;

public class RegistrationAlreadyDoneException extends RuntimeException {

    public RegistrationAlreadyDoneException() {
        super("This email is already registered");
    }

    public RegistrationAlreadyDoneException(String msg) {
        super(msg);
    }
}
