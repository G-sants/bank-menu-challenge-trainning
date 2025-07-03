package g.sants.microservices_communication.domain;

import g.sants.microservices_communication.application.exceptions.errors.CannotChangeLimitException;
import g.sants.microservices_communication.application.exceptions.errors.InsufficientBalanceException;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.*;

import static java.lang.System.out;


@Entity
@Table(name = "Bank_Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private double balance;
    private double accountLimit;
    private String accType;
    private List<Double> histVal;
    private List<String> histCode;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Account(List<Double> histVal, List<String> histCode) {
        this.histVal = histVal;
        this.histCode = histCode;
    }

    public Account(){}

    public void limitValidation() {
        double nVal = 0.0;
        for (Double aDouble : histVal) {
            nVal += Math.abs(aDouble);
        }
        if (nVal > 0) {
            limitChange(nVal);
        } else throw new CannotChangeLimitException();
    }

    public void limitChange(double ver) {
        ver -= accountLimit;
        int menu = 0;
        if (ver > 0) {
            if (ver <= 500) {
                menu = 1;
            }
            if (ver <= 1000 && ver > 500) {
                menu = 2;
            }
            if (ver <= 1500 && ver > 1000) {
                menu = 3;
            }
            if (ver > 1500) {
                menu = 4;
            }

            switch (menu) {
                case 1:
                    accountLimit += 500;
                    System.out.println("Your limit was changed to: " + accountLimit);
                    break;
                case 2:
                    accountLimit += 1000;
                    System.out.println("Your limit was changed to: " + accountLimit);
                    break;
                case 3:
                    accountLimit += 1500;
                    System.out.println("Your limit was changed to: " + accountLimit);
                    break;
                case 4:
                    accountLimit += 2000;
                    System.out.println("Your limit was changed to: " + accountLimit);
                    break;
            }
        } else throw new CannotChangeLimitException();
    }

    public void depAccount(Double deposit) {
        balance += deposit;
        histCode.add("Deposit:");
        histVal.add(deposit);
        out.println("Balance Updated: " + getBalance());
    }

    public void withdrawAccount(Double withdraw) {
        balance -= withdraw;
        histCode.add("Withdrawal:");
        histVal.add(withdraw);
        out.println("Balance Updated: " + getBalance());
    }

    public void transRet(double transfer, long cpf) {
        balance -= transfer;
        /*VALIDAR TRANSFERENCIA
        if (Menu.ListaCPF.containsKey(cpf)) {
            User newuser = Menu.ListaCPF.get(cpf);
            transRec(transfer);
        }*/

        histCode.add("Transfer Made:");
        histVal.add(-transfer);
    }

    private void transRec(double transfer) {
        balance += transfer;
        histCode.add("Transfer Received:");
        histVal.add(transfer);
    }

    public void getHist() {
        Double[] a;
        String[] b;
        b = histCode.toArray(new String[0]);
        a = histVal.toArray(new Double[0]);
        for (int i = 0; i < a.length; i++) {
            System.out.println(b[i]);
            System.out.println(" " + a[i]);
        }
    }

    public boolean timeValidation(){
        LocalTime timeCheckUp = LocalTime.of(22,0);
        LocalTime timeCheckDown = LocalTime.of(5,0);
        LocalTime currentTime = LocalTime.now();
        return !(currentTime.isAfter(timeCheckUp) & currentTime.isBefore(timeCheckDown));
    }
    public boolean balanceCheck(Double price){
        if(getBalance()<price){
            throw new InsufficientBalanceException();
        }
        return true;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAccountLimit() {
        return accountLimit;
    }

    public void setAccountLimit(double accountLimit) {
        this.accountLimit = accountLimit;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
