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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public long getCardID() {
        return cardID;
    }

    public void setCardID(long cardID) {
        this.cardID = cardID;
    }
}
