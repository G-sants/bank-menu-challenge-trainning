import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class BancoDeUsuario {
    static Scanner scanner = new Scanner(in);
    static Map<Long, Usuario> ListaCPF = new HashMap<>();

    static boolean cpfVAL(long n) {
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
                Usuario novoUser1 = new Usuario(cpf, nomeUser, tipo);
                ListaCPF.put(cpf, novoUser1);
                break;
            case 2:
                tipo = "Salário";
                Usuario novoUser2 = new Usuario(cpf, nomeUser, tipo);
                ListaCPF.put(cpf, novoUser2);
                break;
            case 3:
                tipo = "Poupança";
                Usuario novoUser3 = new Usuario(cpf, nomeUser, tipo);
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
