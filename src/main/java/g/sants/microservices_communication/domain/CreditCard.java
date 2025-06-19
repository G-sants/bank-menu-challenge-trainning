package g.sants.microservices_communication.domain;

import jakarta.persistence.Entity;

@Entity
public class CreditCard {

    private String name;
    private String lastName;
    private long customerID;
    private long cardID;

    public CreditCard(String name, String lastName, long customerID, long cardID) {
        this.name = name;
        this.lastName = lastName;
        this.customerID = customerID;
        this.cardID = cardID;
    }
}
