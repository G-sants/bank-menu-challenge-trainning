import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.ScatteringByteChannel;
import java.time.LocalTime;
import java.util.Scanner;
import static java.lang.System.*;

public class MenuBanco {
    static boolean timeValidation(){
        LocalTime timeCheckUp = LocalTime.of(22,0);
        LocalTime timeCheckDown = LocalTime.of(5,0);
        LocalTime currentTime = LocalTime.now();
        if (currentTime.isAfter(timeCheckUp) & currentTime.isBefore(timeCheckDown)) {
            return false;
        } else return true;
    }

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
                        Usuario usuario = BancoDeUsuario.ListaCPF.get(cpf);
                        BancoDeUsuario.accData(usuario);

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
                                        BancoDeUsuario.accData(usuario);
                                    break;

                                    case 2:
                                        usuario.depAccount();
                                        break;

                                    case 3:
                                        boolean testTime = timeValidation();
                                        if(testTime) {
                                            usuario.saqAccount();
                                            break;
                                        }else {
                                            out.println("Sua Transação não é permitida pelo horário");
                                        }continue;

                                    case 4:
                                        usuario.transAccount();
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
                                                    out.println("Seu Limite Atual:" + usuario.getLimite());
                                                    break;
                                                case 2:
                                                    usuario.valLimite();
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
                                                    usuario.getHist();
                                                    break;
                                                case 2:
                                                    String path = "C:\\Microsoft\\hist.csv";
                                                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
                                                        writer.write("Movimentações, Valores(R$)");
                                                        writer.newLine();
                                                        int size = usuario.histVal.size();

                                                        for (int i=0;i<size;i++) {
                                                            writer.write(usuario.histCode.get(i)+","+usuario.histVal.get(i));
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

