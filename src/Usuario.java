import java.lang.reflect.Array;
import java.util.*;

public class Usuario {
    private final long cpf;
    private final List<Integer> conta;
    private double saldo;
    private double limite;
    private final String nomeUser;
    private final String tipoConta;
    private List<Double> histVal;
    private List<String> histCode;

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

/*    public double valLimite() {
        PUXAR HISTÓRICO DE TRANSAÇÕES
        double nVal

        if (nVal > 0) {
            alterarLimite(nVal);
            return;
        } else {
            return
                    System.out.println("Não foi possível alterar seu limite no Momento");
        }
    }
*/

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

    public void depSaldo(double deposito) {
        saldo += deposito;
        histCode.add("Depósito:");
        histVal.add(deposito);
    }

    public void saqSaldo(double saque) {
            if(saldo>=saque) {
                saldo -= saque;
                histCode.add("Saque:");
                histVal.add(saque);
            }else {
                System.out.println("Transação Indisponível, Saldo Insuficiente");
            }
        }

    public void transSaldo(double transfer) {
        if(saldo>=transfer) {
            saldo -= transfer;
            histCode.add("Transferência:");
            histVal.add(transfer);
        }else {
            System.out.println("Transação Indisponível, Saldo Insuficiente");
        }
    }

    public List<Integer> getConta() {
        return conta;
    }

    public double getSaldo() {
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