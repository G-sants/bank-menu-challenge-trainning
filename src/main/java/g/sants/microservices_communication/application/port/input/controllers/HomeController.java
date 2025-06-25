package g.sants.microservices_communication.application.port.input.controllers;

import org.springframework.ui.Model;
import g.sants.microservices_communication.application.services.UserService;
import g.sants.microservices_communication.domain.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/create-account")
    public String showCreateAccountForm(Model model) {
        model.addAttribute("user", new User());
        return "create-account";
    }

    @PostMapping("/create-account")
    public String createAccount(@Valid User user, BindingResult bindingResult,
    RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "create-account";
        }
        userService.createNewAccount(user.getCustomerID(),user.getName(),
                user.getLastName(),
                user.getEmail(), user.getAccType());

        redirectAttributes.addFlashAttribute("successMessage", "Congratulations! User created successfully.");
        return "redirect:/";
    }
}