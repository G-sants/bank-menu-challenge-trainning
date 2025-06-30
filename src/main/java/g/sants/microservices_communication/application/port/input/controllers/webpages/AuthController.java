package g.sants.microservices_communication.application.port.input.controllers.webpages;

import g.sants.microservices_communication.application.dto.RegisterDTORequest;
import g.sants.microservices_communication.application.services.auth.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthorizationService authorizationService;

    @Autowired
    public AuthController(AuthorizationService authorizationService,
                          AuthenticationManager authenticationManager){
        this.authorizationService = authorizationService;
        this.authenticationManager= authenticationManager;
    }

    @PostMapping("/auth/create-account")
    public String register(@RequestParam Long customerID, @RequestParam String name,
                           @RequestParam String lastName, @RequestParam String email,
                           @RequestParam String password, @RequestParam String accType,
                           RedirectAttributes redirectAttributes) {

        try {
            RegisterDTORequest data = new RegisterDTORequest(customerID, name, lastName, email, password, accType);
            authorizationService.registerNewUser (data);

            redirectAttributes.addFlashAttribute("successMessage", "Congratulations! Your registration was successful.");
            return "redirect:/";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Registration failed. Please try again.");
            return "redirect:/create-account";
        }
    }

    @PostMapping("/auth/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(email, password);
            var auth = this.authenticationManager.authenticate(usernamePassword);

            return "redirect:/home";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }
}
