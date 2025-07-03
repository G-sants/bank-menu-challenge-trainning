package g.sants.microservices_communication.application.services;

import g.sants.microservices_communication.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final Account account;


    @Autowired
    public AccountService( Account account) {
        this.account = account;
    }

    public void changeLimit() {
        account.limitValidation();
    }


}