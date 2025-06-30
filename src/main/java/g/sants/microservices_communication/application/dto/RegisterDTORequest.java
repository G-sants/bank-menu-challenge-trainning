package g.sants.microservices_communication.application.dto;



public record RegisterDTORequest(Long customerID, String password, String name, String lastName,
                                 String email, String accType) {
}
