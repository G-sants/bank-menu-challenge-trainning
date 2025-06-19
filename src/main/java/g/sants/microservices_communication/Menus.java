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
    public static Map<Long, User> ListaCPF = new HashMap<>();

    private void menuSelector(int selected) {
        if(selected == 1){}
        if(selected == 2){
            out.println("Digite seu CPF para Entrar");
            long customerID = scanner.nextLong();}
        if(selected == 3){ exitingMenu();}
    }

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

    private void userMenu(){
        if (Menus.ListaCPF.containsKey()) {
            User user = Menus.ListaCPF.get(cpf);
            Menus.accData(user);
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
            }
        }
    }

    public void exitingMenu(){
        out.println("Exiting");
    }
}
