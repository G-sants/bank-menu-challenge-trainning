package g.sants.microservices_communication.application.services;

import g.sants.microservices_communication.communication.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {

    private final RestTemplate restTemplate;

    @Autowired
    public  PaymentService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public Order retrieveOrder(String orderId) {
        return restTemplate.getForObject("http://localhost:8081/checkout/order/" + orderId, Order.class);
    }
}
