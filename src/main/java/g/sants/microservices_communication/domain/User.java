package g.sants.microservices_communication.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import static java.lang.System.out;

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private  Long customerID;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;

    public User(Long customerID){
    }

    public User(long customerID, String name, String lastName, String email) {
        this.customerID = customerID;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public boolean idValidation(long n) {
        if (n < 10000000000L || n > 99999999999L) {
            out.println("Please enter a valid ID");
            return false;
        }else
            return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}