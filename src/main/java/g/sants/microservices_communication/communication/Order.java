package g.sants.microservices_communication.communication;

import java.time.LocalDateTime;

public class Order {

    private String id;
    private double totalPrice;
    private LocalDateTime checkOutDate;

    public Order(String id, double totalPrice, LocalDateTime checkOutDate) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.checkOutDate = checkOutDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
