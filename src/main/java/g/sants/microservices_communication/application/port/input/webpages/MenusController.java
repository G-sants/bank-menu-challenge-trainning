package g.sants.microservices_communication.application.port.input.webpages;

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
import org.springframework.web.bind.annotation.RequestParam;

import java.time.format.DateTimeFormatter;

@Controller
public class MenusController {

    private final PaymentService paymentService;
    private final UserService userService;

    @Autowired
    public MenusController(PaymentService paymentService, UserService userService){
        this.paymentService = paymentService;
        this.userService = userService;
    }

    @GetMapping("/pay/checkout")
    public String payOrder(@RequestParam String orderID, Model model){
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

}
