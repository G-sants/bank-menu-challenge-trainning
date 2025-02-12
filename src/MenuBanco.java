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
                        out.println("Acessando conta do usuário: " + usuario.getNomeUser());
                        out.println("Nome: " + usuario.getNomeUser());
                        out.println("Conta: " + usuario.getConta());
                        out.println("limite Disponível: " + usuario.getLimite());
                        out.println("Saldo: " + usuario.getSaldo());
                        out.println("Conta do Tipo: " + usuario.getTipoConta());

                        while (true) {
                            out.println("\nEscolha o que Deseja Fazer");
                            out.println("1 - Depósito");
                            out.println("2 - Saque");
                            out.println("3 - Transferência");
                            out.println("4 - Verificar/Alterar Limite");
                            out.println("5 - Verificar Histório de Transações");
                            out.println("6 - Sair");

                            out.println("\nDigite a Opção");

                            if (!scanner.hasNextInt()) {
                                out.println("Por favor selecione uma das Opções (Números Apenas)");
                                scanner.next();

                            } else { menuUsuario= scanner.nextInt();

                                if (menuUsuario == 6) {
                                    out.println("Saindo...");
                                    break;
                                }

                                switch (menuUsuario) {
                                    case 1:
                                        usuario.depAccount();
                                        break;

                                    case 2:
                                        boolean testTime = timeValidation();
                                        if(testTime) {
                                            usuario.saqAccount();
                                            break;
                                        }else {
                                            out.println("Sua Transação não é permitida pelo horário");
                                        }continue;

                                    case 3:
                                        Usuario.transAccount();
                                        break;

                                    case 4:
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
                                    case 5:
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

                                                    //FAZER COMANDO DE EXPORTAR MAPA EM CSV
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

