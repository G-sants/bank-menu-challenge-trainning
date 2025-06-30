package g.sants.microservices_communication.application.exceptions;

import g.sants.microservices_communication.application.exceptions.errors.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ErrorsResponse> handleLoginException
            (LoginException exception){
        ErrorsResponse errorsResponse = new ErrorsResponse(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(),
                "Invalid Login",exception.getMessage());
        return new ResponseEntity<>(errorsResponse,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<ErrorsResponse> handleLoginException
            (LoginFailedException exception){
        ErrorsResponse errorsResponse = new ErrorsResponse(LocalDateTime.now(), HttpStatus.FORBIDDEN.value(),
                "Authentication Failed",exception.getMessage());
        return new ResponseEntity<>(errorsResponse,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorsResponse> userNotFoundException(UserNotFoundException exception){
        ErrorsResponse errorsResponse = new ErrorsResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
                "User not Found with the ID given", exception.getMessage());
        return new ResponseEntity<>(errorsResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoInfoReceivedException.class)
    public ResponseEntity<ErrorsResponse> noInfoReceivedException(NoInfoReceivedException exception){
        ErrorsResponse errorsResponse = new ErrorsResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
                "The service returned a null response to the request", exception.getMessage());
        return new ResponseEntity<>(errorsResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RegistrationAlreadyDoneException.class)
    public ResponseEntity<ErrorsResponse> userAlreadyRegisteredException(RegistrationAlreadyDoneException exception){
        ErrorsResponse errorsResponse = new ErrorsResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
                "This email has already been used to register an user", exception.getMessage());
        return new ResponseEntity<>(errorsResponse,HttpStatus.NOT_FOUND);
    }
}
