package g.sants.microservices_communication.application.port.input.webpages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping("/pay")
    public String showPaymentPage(){return "payment";}

    @GetMapping("/auth/logout")
    public String logout() {
        return "redirect:/login";
    }

}
