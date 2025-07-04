package g.sants.microservices_communication.application.port.input.webpages;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import g.sants.microservices_communication.domain.User;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/create-account")
    public String showCreateAccountForm(Model model) {
        model.addAttribute("user", new User());
        return "create-account";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        return "login";
    }

    @GetMapping("/user-menu")
    public String userMenu(Model model, Authentication auth) {
        String username = auth.getIdentification();
        model.addAttribute("username", username);
        return "user-menu";
    }
}