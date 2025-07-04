package g.sants.microservices_communication.application.port.input.webpages;

import g.sants.microservices_communication.application.dto.LoginDTORequest;
import g.sants.microservices_communication.application.dto.RegisterDTORequest;
import g.sants.microservices_communication.application.services.auth.AuthService;
import g.sants.microservices_communication.application.services.auth.TokenService;
import g.sants.microservices_communication.domain.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public AuthController(AuthService authService, AuthenticationManager authenticationManager,
                          TokenService tokenService){
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/auth/create-account")
    public String register(@RequestParam Long customerID, @RequestParam String password,
                           @RequestParam String name, @RequestParam String lastName,
                           @RequestParam String email, @RequestParam String accType,
                           RedirectAttributes redirectAttributes) {

        try {
            RegisterDTORequest data = new RegisterDTORequest(customerID, password, name, lastName, email, accType);
            authService.registerNewUser(data);

            redirectAttributes.addFlashAttribute("successMessage", "Congratulations! Your registration was successful.");
            return "redirect:/";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Registration failed. Please try again.");
            return "redirect:/create-account";
        }
    }

    @PostMapping("/auth/login")
    public String login(@RequestParam String email, @RequestParam String password,
                        Model model, HttpServletResponse response) {

        LoginDTORequest data = new LoginDTORequest(email,password);

        var usernamePassword = new UsernamePasswordAuthenticationToken
                (data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        tokenService.addTokenToCookie(response, token);

            return "redirect:/user-menu";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
    }


    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        tokenService.removeTokenCookie(response);
        return "redirect:/login";
    }
}
