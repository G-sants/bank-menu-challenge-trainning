package g.sants.microservices_communication.application.services;

import g.sants.microservices_communication.application.port.output.AccountRepository;
import g.sants.microservices_communication.domain.Account;
import g.sants.microservices_communication.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

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

    private final Scanner scanner = new Scanner(in);

    public void accDep() {
        double deposit = valueValidation();
        account.depAccount(deposit);
    }

    public void accWith() {
        double withdraw = valueValidation();
        account.withdrawAccount(withdraw);
    }

    public void transAccount() {
        double transfer;
        long customerID;
        transfer = valueValidation();
        customerID = idCheck();
        if (account.getBalance() > transfer) {
            account.transRet(transfer, customerID);
            out.println("balance Atualizado: " + account.getBalance());
        } else if (account.getBalance() < transfer) {
            out.println("balance Inuficiente");
        } else {
            out.println("Horário não permite a Transação");
        }
    }


    public double valueValidation() {
       while(true) {
           out.println("\nType the value");
           if (!scanner.hasNextDouble()) {
               out.println("Invalid value, try again.");
               scanner.next();
               continue;
           }
           break;
       }
        return scanner.nextDouble();
    }

    public long idCheck() {
        out.println("Type the receiver's ID");
        while (true) {
            if (!scanner.hasNextLong()) {
                out.println("Please type a valid ID (Numbers Only)");
                scanner.next();
                continue;
            }
            break;
        }
        while (true) {
            if (!user.idValidation(scanner.nextLong())) {
                out.println("Please type a valid ID)");
                scanner.next();
                continue;
            }
            break;
        }
        return scanner.nextLong();
    }
}