package g.sants.microservices_communication.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
}