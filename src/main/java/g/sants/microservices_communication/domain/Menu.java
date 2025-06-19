package g.sants.microservices_communication;

import g.sants.microservices_communication.domain.Account;
import g.sants.microservices_communication.domain.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class Menus {
    static Scanner scanner = new Scanner(in);
    public Map<Long, User> ListaCPF = new HashMap<>();

    public void startMenu() {
        int startingMenu;

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
            startingMenu = scanner.nextInt();
            menuSelector(startingMenu);
        }
    }

    public void exitingMenu(){
        out.println("Exiting");
    }

    public void userMenu(){
        if (Menu.ListaCPF.containsKey(cpf)) {
            User user = Menu.ListaCPF.get(cpf);
            Menu.accData(user);
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
                    out.println("Please select one of the Options (Numbers Only)");
                    scanner.next();

                } else { userMenu = scanner.nextInt();

                    if (userMenu == 7) {
                        out.println("Exiting...");
                        break;
                    }

                    switch (userMenu) {
                        case 1:
                            Menu.accData(user);
                            break;

                        case 2:
                            Account.depAccount();
                            break;

                        case 3:
                            boolean testTime = Account.timeValidation();
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
                                    limitMenu = scanner.nextInt();
                                } else {
                                    out.println("Por favor escolha uma opção Válida");
                                    scanner.next();
                                    continue;
                                }
                                switch (limitMenu) {
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
                                    limitMenu = scanner.nextInt();
                                } else {
                                    out.println("Por favor escolha uma opção Válida");
                                    scanner.next();
                                    continue;
                                }

                                switch (limitMenu) {
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

private void menuSelector(int selected) {
    if(selected == 1){}
    if(selected == 2){
        out.println("Digite seu CPF para Entrar");
        int cpf = scanner.nextLong();}
    if(selected == 3){ exitingMenu();}
}

public void accData(User user) {
    out.println("Acessando dados da conta do usuário: " + user.getName()
            + user.getLastName());
    out.println("Nome: " + user.getName() +user.getLastName());
    out.println("limite Disponível: " + Account.getLimit());
    out.println("Saldo: " + Account.getBalance());
    out.println("Conta do Tipo: " + user.getTipoConta());
}



private void createUser(long cpf, String nomeUser, int menuConta) {
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

public void newUser() {
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

}
