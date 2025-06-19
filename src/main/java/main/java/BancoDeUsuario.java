package main.java;

import g.sants.microservices_communication.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class BancoDeUsuario {
    static Scanner scanner = new Scanner(in);
    public static Map<Long, User> ListaCPF = new HashMap<>();

    public static void accData(User user) {
        out.println("Acessando dados da conta do usuário: " + user.getNomeUser());
        out.println("Nome: " + user.getNomeUser());
        out.println("Conta: " + user.getConta());
        out.println("limite Disponível: " + user.getLimite());
        out.println("Saldo: " + user.getSaldo());
        out.println("Conta do Tipo: " + user.getTipoConta());
    }
    public static boolean cpfVAL(long n) {
        if (n < 10000000000L || n > 99999999999L) {
            out.println("Por favor digite um CPF válido");
            return false;
        }else
            return true;
    }

    private static void createUser(long cpf, String nomeUser, int menuConta) {
        String tipo;
        switch (menuConta) {
            case 1:
                tipo = "Corrente";
                User novoUser1 = new User(cpf, nomeUser, tipo, email);
                ListaCPF.put(cpf, novoUser1);
                break;
            case 2:
                tipo = "Salário";
                User novoUser2 = new User(cpf, nomeUser, tipo, email);
                ListaCPF.put(cpf, novoUser2);
                break;
            case 3:
                tipo = "Poupança";
                User novoUser3 = new User(cpf, nomeUser, tipo, email);
                ListaCPF.put(cpf, novoUser3);
                break;
        }
    }

    public static void newUser() {
        while (true) {
            out.print("Digite seu CPF: ");

            if (!scanner.hasNextLong()) {
                out.println("Por Favor digite um CPF válido, números apenas");
                scanner.next();
                continue;

            }  else {
                boolean f;
                long cpf=scanner.nextLong();
                f= cpfVAL(cpf);

                if (!f){
                    continue;

                } else {
                    scanner.nextLine();
                    out.print("\nDigite seu Nome: ");
                    String nomeUser  = scanner.nextLine();

                    if (nomeUser == null) {
                        out.println("Por favor Digite seu Nome Novamente");
                        continue;

                    } else {
                        while (true) {
                            out.println("\nEscolha o Tipo de conta que deseja Criar");
                            out.println("1 - Corrente");
                            out.println("2 - Salário");
                            out.println("3 - Poupança");

                            if (!scanner.hasNextInt()) {
                                out.println("Por favor Escolha uma Opção Válida");
                                scanner.next();
                                continue;
                            }
                            int menuConta = scanner.nextInt();

                            if (menuConta <1 || menuConta > 3 ) {
                                out.println("Por favor Escolha uma Opção Válida");
                                scanner.next();
                                continue;
                            }
                            createUser(cpf, nomeUser, menuConta);
                            out.println("\nParabéns pelo Cadastro");
                            break;
                        }
                    }
                }
            }
            break;
        }
    }

    public static boolean userLogin() {

        return true;
    }
}
