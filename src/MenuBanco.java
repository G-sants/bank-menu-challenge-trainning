import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static java.lang.System.*;

public class MenuBanco {

    static boolean cadastroVerVal(long n) {
        if (n < 10000000000L || n > 99999999999L) {
            out.println("Por favor digite um CPF válido");
            return false;
        }else
            return true;
    }

    private static void criarConta(long cpf, String nomeUser, int menuConta) {
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

    private static Map<Long, Usuario> ListaCPF = new HashMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(in);
        String nomeUser;
        long  cpf;
        int menuInicial, menuUsuario, menuConta, menuLimite;
        double deposito, saque, transfer;



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
                    while (true) {
                        out.print("Digite seu CPF: ");

                        if (!scanner.hasNextLong()) {
                            out.println("Por Favor digite um CPF válido, números apenas");
                            scanner.next();
                            continue;

                        }  else {
                            boolean f;
                            cpf=scanner.nextLong();
                            f=cadastroVerVal(cpf);

                            if (!f){
                            continue;

                            } else {
                                scanner.nextLine();
                                out.print("\nDigite seu Nome: ");
                                nomeUser  = scanner.nextLine();

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
                                        menuConta = scanner.nextInt();

                                        if (menuConta <1 || menuConta > 3 ) {
                                            out.println("Por favor Escolha uma Opção Válida");
                                            scanner.next();
                                            continue;
                                        }
                                        criarConta(cpf, nomeUser, menuConta);
                                        out.println("\nParabéns pelo Cadastro");
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    }
                    break;
                case 2:
                    out.println("Digite seu CPF para Entrar");
                    cpf = scanner.nextLong();

                    if (ListaCPF.containsKey(cpf)) {
                        Usuario usuario = ListaCPF.get(cpf);
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
                                continue;

                            } else { menuUsuario= scanner.nextInt();

                                if (menuUsuario == 6) {
                                    out.println("Saindo...");
                                    break;
                                }

                                switch (menuUsuario) {
                                    case 1:
                                        out.println("\nDigite o Valor do Depósito");
                                        if (scanner.hasNextDouble()) {
                                            deposito = scanner.nextDouble();
                                            usuario.depSaldo(deposito);
                                            out.println("Saldo Atualizado: " + usuario.getSaldo());
                                        } else {
                                            out.println("Valor inválido. Tente novamente.");
                                            scanner.next();
                                        }
                                        break;

                                    case 2:
                                        out.println("\nDigite o Valor do Saque");
                                        if (scanner.hasNextDouble()) {
                                            saque = scanner.nextDouble();
                                            if (usuario.getSaldo() > saque) {
                                                usuario.saqSaldo(saque);
                                                out.println("Saldo Atualizado: " + usuario.getSaldo());
                                            } else {
                                                out.println("Saldo Inuficiente");
                                            }
                                        } else {
                                            out.println("Valor inválido. Tente novamente.");
                                            scanner.next();
                                        }
                                        break;

                                    case 3:
                                        out.println("\nDigite o Valor da Transferência");
                                        if (scanner.hasNextDouble()) {
                                            transfer = scanner.nextDouble();

                                            if (usuario.getSaldo() > transfer) {
                                                usuario.transSaldo(transfer);
                                                out.println("Saldo Atualizado: " + usuario.getSaldo());

                                                /* ADICIONAR RECEBIMENTO PARA OUTRO USUÁRIO*/

                                            } else {
                                                out.println("Saldo Inuficiente");
                                            }

                                        } else {
                                            out.println("Valor inválido. Tente novamente.");
                                            scanner.next();
                                        }
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
                                                    double vLimite = 0.0;
                                               /* vLimite = usuario.valLimite();
                                                if (vLimite) {
                                                     usuario.alterarLimite(nLimite);
                                                } else {
                                                     out.println("Não é possivel Alterar seu Limite no Momento");
                                                }
                                                break;*/
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

