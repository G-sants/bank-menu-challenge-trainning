package g.sants.microservices_communication.application.services;

import g.sants.microservices_communication.application.port.output.AccountRepository;
import g.sants.microservices_communication.domain.Account;
import g.sants.microservices_communication.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private Account account;
    private User user;

    @Autowired
    public AccountService(AccountRepository accountRepository, Account account
    ,User user) {
        this.accountRepository = accountRepository;
        this.account = account;
        this.user = user;
    }

}