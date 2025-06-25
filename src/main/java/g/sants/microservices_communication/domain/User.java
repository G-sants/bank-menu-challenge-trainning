package g.sants.microservices_communication.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import static java.lang.System.out;

@Entity
@Table(name = "Bank_Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "CustomerID is required")
    private Long customerID;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Last Name is required")
    private String lastName;
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    private String accType;

    public User (Long customerID, String name, String lastName,
                 String email, String accType) {
        this.customerID = customerID;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.accType = accType;
    }

    public User() {

    }
    public boolean idValidation(long n) {
        if (n < 10000000000L || n > 99999999999L) {
            out.println("Please enter a valid ID");
            return false;
        }else
            return true;
    }

    public void accUserData() {
        out.println("Accessing user data: ");
        out.println("Name: " );
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

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }
}