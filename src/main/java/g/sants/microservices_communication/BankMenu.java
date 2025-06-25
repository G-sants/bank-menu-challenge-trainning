package g.sants.microservices_communication;

import g.sants.microservices_communication.application.port.input.Menus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankMenu {

    public static void main(String[] args) {
        SpringApplication.run(BankMenu.class, args);
        new Menus().startMenu();
    }
}