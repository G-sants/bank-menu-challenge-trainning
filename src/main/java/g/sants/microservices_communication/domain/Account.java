package g.sants.microservices_communication.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalTime;
import java.util.*;

import static java.lang.System.out;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Use AUTO or SEQUENCE based on your DB
    private UUID uuid;
    private  List<Integer> acc;
    private double balance;
    private double limit;
    private String accType;
    private List<Double> histVal;
    private List<String> histCode;

    public Account(List<Integer> acc, double balance, double limit,
                   String accType, List<Double> histVal, List<String> histCode) {
        this.acc = accGenerate();
        this.balance = balance;
        this.limit = limit;
        this.accType = accType;
        this.histVal = histVal;
        this.histCode = histCode;
    }
    public Account(){}

    private List<Integer> accGenerate() {
        List<Integer> acc = new ArrayList<>();
        Random random = new Random();
        int numAg = random.nextInt(9999);
        int numOp = random.nextInt(999);
        int numCont = random.nextInt(99999999);
        int numVer = random.nextInt(9);
        acc.add(numAg);
        acc.add(numCont);
        acc.add(numOp);
        acc.add(numVer);
        return acc;
    }

    public void limitValidation() {
        double nVal = 0.0;
        for (Double aDouble : histVal) {
            nVal += Math.abs(aDouble);
        }
        if (nVal > 0) {
            limitChange(nVal);
        } else {
            System.out.println("your limit cannot be change right now, Please try again later");
        }

    }


    public void limitChange(double ver) {
        ver -= limit;
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
                    double lim = 500;
                    limit += 500;
                    System.out.println("Seu limit foi alterado para: " + limit);
                    break;
                case 2:
                    limit += 1000;
                    System.out.println("Seu limit foi alterado para: " + limit);
                    break;
                case 3:
                    limit += 1500;
                    System.out.println("Seu limit foi alterado para: " + limit);
                    break;
                case 4:
                    limit += 2000;
                    System.out.println("Seu limit foi alterado para: " + limit);
                    break;
            }
        } else {
            System.out.println("No momento não é possivel alterar seu limit");
        }
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

    public void accAccData() {
        Account account = new Account();
        out.println("Your Balance is " + account.getBalance());
        out.println("Your Limit is " + account.getLimit());
        out.println("Your Account is " + account.getAccType());
    }

    public List<Integer> getAcc() {
        return acc;
    }

    public void setAcc(List<Integer> acc) {
        this.acc = acc;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
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
}
