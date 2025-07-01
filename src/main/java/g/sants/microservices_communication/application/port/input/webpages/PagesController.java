package g.sants.microservices_communication.application.port.input.webpages;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagesController {

    @GetMapping("/pay")
    public String paymentMenu() {
        return "payment";
    }

    @GetMapping("/auth/logout")
    public String logout() {
        return "redirect:/login";
    }


}
