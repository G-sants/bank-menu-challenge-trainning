import java.util.*;
import java.util.List;

import static java.lang.System.in;
import static java.lang.System.out;

public class Usuario {
    private final long cpf;
    private final List<Integer> conta;
    private static double saldo;
    private double limite;
    private final String nomeUser;
    private final String tipoConta;
    private static List<Double> histVal;
    private static List<String> histCode;

    public Usuario(long cpf, String nomeUser, String tipoConta) {
        this.cpf = cpf;
        this.nomeUser = nomeUser;
        this.conta = gerarConta();
        this.saldo = 0.0;
        this.limite = 1413.90;
        this.tipoConta = tipoConta;
        this.histVal = new ArrayList<>();
        this.histCode = new ArrayList<>();
    }

    static Scanner scanner = new Scanner(in);

    private List<Integer> gerarConta() {
        List<Integer> lista = new ArrayList<>();
        Random random = new Random();
        int numAg = random.nextInt(9999);
        int numOp = random.nextInt(999);
        int numCont = random.nextInt(99999999);
        int numVer = random.nextInt(9);
        lista.add(numAg);
        lista.add(numCont);
        lista.add(numOp);
        lista.add(numVer);
        return lista;
    }

    public void valLimite() {
        double nVal=0.0;
        for (int i=0;i < histVal.size();i++) {
            nVal += Math.abs(histVal.get(i));
        }
        if (nVal > 0) {
            alterarLimite(nVal);
        } else {
            System.out.println("Não foi possível alterar seu limite no Momento");
        }

    }


    public void alterarLimite(double ver){
        ver -= limite;
        int menu = 0;
        if (ver>0) {
            if (ver<=500) {menu=1;}
            if (ver<=1000 && ver> 500) {menu=2;}
            if (ver<=1500 && ver>1000) {menu=3;}
            if (ver<1500) {menu=4;}

            switch(menu) {
                case 1:
                    double lim = 500;
                    limite += 500;
                    System.out.println("Seu limite foi alterado para: " + limite);
                    break;
                case 2:
                    limite += 1000;
                    System.out.println("Seu limite foi alterado para: " + limite);
                    break;
                case 3:
                    limite += 1500;
                    System.out.println("Seu limite foi alterado para: " + limite);
                    break;
                case 4:
                    limite += 2000;
                    System.out.println("Seu limite foi alterado para: " + limite);
                    break;
            }
        } else {
            System.out.println("No momento não é possivel alterar seu Limite");
        }
    }

    public void depAccount() {
        double deposito;
        out.println("\nDigite o Valor do Depósito");
        if (scanner.hasNextDouble()) {
            deposito = scanner.nextDouble();
            saldo += deposito;
            histCode.add("Depósito:");
            histVal.add(deposito);
            out.println("Saldo Atualizado: " + getSaldo());
        } else {
            out.println("Valor inválido. Tente novamente.");
            scanner.next();
        }
    }

    public void saqAccount () {
        out.println("\nDigite o Valor do Saque");
        if (scanner.hasNextDouble()) {
            double saque = scanner.nextDouble();
            if (getSaldo() > saque) {
                saldo -= saque;
                histCode.add("Saque:");
                histVal.add(-saque);
                out.println("Saldo Atualizado: " + getSaldo());
            } else {
                out.println("Saldo Insuficiente");
            }
        } else {
            out.println("Valor inválido. Tente novamente.");
            scanner.next();
        }
    }

    public static void transAccount() {
        double transfer;
        long menuTransfer;
        out.println("\nDigite o Valor da Transferência");
        if (scanner.hasNextDouble()) {
            transfer = scanner.nextDouble();

            if (getSaldo() > transfer & MenuBanco.timeValidation()) {
                out.println("Digite o CPF que irá receber");
                while (true) {
                    if (!scanner.hasNextLong()) {
                        out.println("Por Favor digite um CPF válido (apenas Números)");
                        scanner.next();
                        continue;
                    }
                    menuTransfer = scanner.nextLong();
                    boolean t = BancoDeUsuario.cpfVAL(menuTransfer);

                    if (!t) {
                        continue;
                    }
                    break;
                }
                transRet(transfer, menuTransfer);
                out.println("Saldo Atualizado: " + getSaldo());

            } else if(getSaldo()<transfer){
                out.println("Saldo Inuficiente");
            } else{
                out.println("Horário não permite a Transação");
            }

        } else {
            out.println("Valor inválido. Tente novamente.");
            scanner.next();
        }
    }

    public static void transRet(double transfer, long cpf) {
        if(saldo>=transfer) {
            saldo -= transfer;
            if(BancoDeUsuario.ListaCPF.containsKey(cpf)) {
                Usuario user = BancoDeUsuario.ListaCPF.get(cpf);
                user.transRec(transfer);
            }
            histCode.add("Transferência Relizada:");
            histVal.add(-transfer);
        }else {
            System.out.println("Transação Indisponível, Saldo Insuficiente");
        }
    }

    private void transRec(double transfer) {
        saldo += transfer;
        histCode.add("Transferência Recebida:");
        histVal.add(transfer);
    }

    public List<Integer> getConta() {
        return conta;
    }

    public static double getSaldo() {
        return saldo;
    }

    public double getLimite() {
        return limite;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void getHist() {
        Double[] a;
        String[] b;
        b = histCode.toArray(new String[0]);
        a = histVal.toArray(new Double[0]);
        for (int i = 0;i<a.length; i++) {
            System.out.println(b[i]);
            System.out.println(" " + a[i]);
        }
    }
}