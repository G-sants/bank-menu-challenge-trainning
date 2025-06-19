package g.sants.microservices_communication;

import g.sants.microservices_communication.domain.Account;
import g.sants.microservices_communication.domain.User;
import main.java.BancoDeUsuario;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.*;

@SpringBootApplication
public class MenuBanco {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(in);
        long  cpf;
        int menuInicial, menuUsuario, menuLimite;


        while (true) {
            out.println("\nEscolha a Opção Desejada");
            out.println("1 - Criar Nova Conta");
            out.println("2 - Acessar Conta");
            out.println("3 - Sair");

            out.println("\nDigite a Opção");
            if (!scanner.hasNextInt()) {
                out.println("Por favor selecione uma das Opções (Números Apenas)");
                scanner.next();
                continue;
            }

            menuInicial = scanner.nextInt();

            if (menuInicial == 3) {
                out.println("Saindo...");
                break;
            }
            switch (menuInicial) {
                case 1:
                        BancoDeUsuario.newUser();
                    break;
                case 2:
                    out.println("Digite seu CPF para Entrar");
                    cpf = scanner.nextLong();

                    if (BancoDeUsuario.ListaCPF.containsKey(cpf)) {
                        User user = BancoDeUsuario.ListaCPF.get(cpf);
                        BancoDeUsuario.accData(user);

                        while (true) {
                            out.println("\nEscolha o que Deseja Fazer");
                            out.println("1 - Verificar Dados");
                            out.println("2 - Depósito");
                            out.println("3 - Saque");
                            out.println("4 - Transferência");
                            out.println("5 - Verificar/Alterar Limite");
                            out.println("6 - Verificar Histório de Transações");
                            out.println("7 - Sair");

                            out.println("\nDigite a Opção");

                            if (!scanner.hasNextInt()) {
                                out.println("Por favor selecione uma das Opções (Números Apenas)");
                                scanner.next();

                            } else { menuUsuario= scanner.nextInt();

                                if (menuUsuario == 7) {
                                    out.println("Saindo...");
                                    break;
                                }

                                switch (menuUsuario) {
                                    case 1:
                                        BancoDeUsuario.accData(user);
                                    break;

                                    case 2:
                                        Account.depAccount();
                                        break;

                                    case 3:
                                        boolean testTime = timeValidation();
                                        if(testTime) {
                                            Account.saqAccount();
                                            break;
                                        }else {
                                            out.println("Sua Transação não é permitida pelo horário");
                                        }continue;

                                    case 4:
                                        Account.transAccount();
                                        break;

                                    case 5:
                                        out.println("\n Escolha o que Deseja Fazer");

                                        while (true) {
                                            out.println("1 - Verificar Limite");
                                            out.println("2 - Alterar Limite");

                                            if (scanner.hasNextInt() || scanner.nextInt() <= 2) {
                                                menuLimite = scanner.nextInt();
                                            } else {
                                                out.println("Por favor escolha uma opção Válida");
                                                scanner.next();
                                                continue;
                                            }
                                            switch (menuLimite) {
                                                case 1:
                                                    out.println("Seu Limite Atual:" + Account.getLimit());
                                                    break;
                                                case 2:
                                                    Account.limitValidation();
                                            }
                                            break;
                                        }
                                        break;
                                    case 6:
                                        out.println("\n Escolha o que Deseja Fazer");

                                        while (true) {
                                            out.println("1 - Verificar Histórico");
                                            out.println("2 - Exportar Histórico");

                                            if (scanner.hasNextInt() || scanner.nextInt() <= 2) {
                                                menuLimite = scanner.nextInt();
                                            } else {
                                                out.println("Por favor escolha uma opção Válida");
                                                scanner.next();
                                                continue;
                                            }

                                            switch (menuLimite) {
                                                case 1:
                                                    out.println("\nGerando dados...");
                                                    Account.getHist();
                                                    break;
                                                case 2:
                                                    String path = "C:\\Microsoft\\hist.csv";
                                                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
                                                        writer.write("Movimentações, Valores(R$)");
                                                        writer.newLine();
                                                        int size = Account.histVal.size();

                                                        for (int i=0;i<size;i++) {
                                                            writer.write(Account.histCode.get(i)+","+ Account.histVal.get(i));
                                                            writer.newLine();
                                                        }
                                                        out.println("Dados enviados ao Usuário");
                                                    }catch (IOException e) {
                                                            e.printStackTrace();
                                                    }
                                                    break;
                                            }
                                            break;
                                        }
                                }
                            }
                        }
                    } else {
                        out.println("CPF não encontrado, tente novamente");
                        scanner.nextLine();
                        continue;
                    }
                default:
                    out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }
}

