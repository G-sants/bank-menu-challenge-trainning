package g.sants.microservices_communication.domain;

import jakarta.persistence.Entity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

@Entity
public class Account {

    private static final Scanner scanner = new Scanner(in);

    private  List<Integer> acc;
    private double balance;
    private double limit;
    private String accType;
    private final List<Double> histVal;
    private final List<String> histCode;

    public Account(List<Integer> acc, double balance, double limit,
                   String accType, List<Double> histVal, List<String> histCode) {
        this.acc = accGenerate();
        this.balance = balance;
        this.limit = limit;
        this.accType = accType;
        this.histVal = histVal;
        this.histCode = histCode;
    }

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
            System.out.println("Não foi possível alterar seu limit no Momento");
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

    public void depAccount() {
        double deposito;
        out.println("\nDigite o Valor do Depósito");
        if (scanner.hasNextDouble()) {
            deposito = scanner.nextDouble();
            balance += deposito;
            histCode.add("Depósito:");
            histVal.add(deposito);
            out.println("balance Atualizado: " + getBalance());
        } else {
            out.println("Valor inválido. Tente novamente.");
            scanner.next();
        }
    }

    public void saqAccount() {
        out.println("\nDigite o Valor do Saque");
        if (scanner.hasNextDouble()) {
            double saque = scanner.nextDouble();
            if (getBalance() > saque && timeValidation()) {
                balance -= saque;
                histCode.add("Saque:");
                histVal.add(-saque);
                out.println("balance Atualizado: " + getBalance());

            } else if (getBalance() < saque) {
                out.println("balance Inuficiente");
            } else {
                out.println("Horário não permite a Transação");
            }
        } else {
            out.println("Valor inválido. Tente novamente.");
            scanner.next();
        }
    }

    public void transAccount() {
        double transfer;
        long menuTransfer;
        out.println("\nDigite o Valor da Transferência");
        if (scanner.hasNextDouble()) {
            transfer = scanner.nextDouble();

            if (getBalance() > transfer && timeValidation()) {
                out.println("Digite o CPF que irá receber");
                while (true) {
                    if (!scanner.hasNextLong()) {
                        out.println("Por Favor digite um CPF válido (apenas Números)");
                        scanner.next();
                        continue;
                    }
                    menuTransfer = scanner.nextLong();
                    boolean t = main.java.BancoDeUsuario.cpfVAL(menuTransfer);

                    if (!t) {
                        continue;
                    }
                    break;
                }
                transRet(transfer, menuTransfer);
                out.println("balance Atualizado: " + getBalance());

            } else if (getBalance() < transfer) {
                out.println("balance Inuficiente");
            } else {
                out.println("Horário não permite a Transação");
            }

        } else {
            out.println("Valor inválido. Tente novamente.");
            scanner.next();
        }
    }

    public void transRet(double transfer, long cpf) {
        balance -= transfer;
        if (main.java.BancoDeUsuario.ListaCPF.containsKey(cpf)) {
            User user = main.java.BancoDeUsuario.ListaCPF.get(cpf);
            transRec(transfer);
        }
        histCode.add("Transferência Relizada:");
        histVal.add(-transfer);
    }

    private void transRec(double transfer) {
        balance += transfer;
        histCode.add("Transferência Recebida:");
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
}
