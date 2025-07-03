package g.sants.microservices_communication.application.port.input.webpages;

import g.sants.microservices_communication.application.exceptions.errors.CannotChangeLimitException;
import g.sants.microservices_communication.application.services.AccountService;
import g.sants.microservices_communication.application.services.PaymentService;
import g.sants.microservices_communication.application.services.UserService;
import g.sants.microservices_communication.communication.Order;
import g.sants.microservices_communication.domain.Account;
import g.sants.microservices_communication.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;

@Controller
public class MenusController {

    private final PaymentService paymentService;
    private final UserService userService;
    private final AccountService accountService;

    @Autowired
    public MenusController(PaymentService paymentService, UserService userService,
                           AccountService accountService){
        this.paymentService = paymentService;
        this.userService = userService;
        this.accountService = accountService;
    }

    @GetMapping("/pay/checkout")
    public String seeOrder(@RequestParam String orderID, Model model){
        Order order = paymentService.retrieveOrder(orderID);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = order.getCheckOutDate().format(formatter);

        model.addAttribute("order",order);
        model.addAttribute("formattedCheckOutDate", formattedDate);

        return "display-order-info";
    }

    @GetMapping("/user/view-data")
    public String viewAccData(@PathVariable Long userID,
                              Model model){
        User user = userService.getUser(userID);
        Account account = user.getAccount();

        model.addAttribute("user",user);
        model.addAttribute("account",account);
        return "display-user-info";
    }

    @GetMapping("/account/change-limit")
    public String changeUserLimit(@PathVariable Long userID, Model model,
                                  RedirectAttributes redirectAttributes){
        User user = userService.getUser(userID);
        Account account = user.getAccount();

        try {
            accountService.changeLimit();

            model.addAttribute("user", user);
            model.addAttribute("account", account);
            redirectAttributes.addFlashAttribute("limitUpdateMessage","Congratulations your limit was successfully changed.");
            return "redirect:/display-user-info";

        }catch (CannotChangeLimitException e) {
            redirectAttributes.addFlashAttribute("limitErrorMessage", "Limit Change Failed. Requirements not Meet.");
            return "redirect:/display-user-info";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("limitError2Message", "Limit Change Failed. Please try Again.");
            return "redirect:/display-user-info";
        }
    }

    @PostMapping("/pay/pay-order")
    public String payOrder(@PathVariable Long userId, @PathVariable Double price){
        paymentService.payOrder(userId,price);
        return "redirect:/payment";
    }
}
