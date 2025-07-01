package g.sants.microservices_communication.application.port.input.webpages;

import g.sants.microservices_communication.application.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MenusController {

    private final PaymentService paymentService;

    @Autowired
    public MenusController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/pay/checkout")
    public void payOrder(@RequestParam String orderID){
        paymentService.retrieveOrder(orderID);
    }
}
