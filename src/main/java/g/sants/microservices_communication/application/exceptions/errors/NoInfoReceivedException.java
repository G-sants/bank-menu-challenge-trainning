package g.sants.microservices_communication.application.exceptions.errors;

public class NoInfoReceivedException extends RuntimeException {

    public NoInfoReceivedException() {
        super("No information received from the Service");
    }

    public NoInfoReceivedException(String msg) {
        super(msg);
    }
}
