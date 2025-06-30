package g.sants.microservices_communication.application.port.input.controllers.webpages;

import org.springframework.ui.Model;
import g.sants.microservices_communication.application.services.UserService;
import g.sants.microservices_communication.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

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
    public String showLoginPage() {
        return "login";
    }

}