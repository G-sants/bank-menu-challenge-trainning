package g.sants.microservices_communication.application.port.input.webpages;

import g.sants.microservices_communication.application.services.PaymentService;
import g.sants.microservices_communication.communication.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.format.DateTimeFormatter;

@Controller
public class MenusController {

    private final PaymentService paymentService;

    @Autowired
    public MenusController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/pay/checkout")
    public String payOrder(@RequestParam String orderID, Model model){
        Order order = paymentService.retrieveOrder(orderID);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = order.getCheckOutDate().format(formatter);

        model.addAttribute("order",order);
        model.addAttribute("formattedCheckOutDate", formattedDate);

        return "display-order-info";
    }
}
