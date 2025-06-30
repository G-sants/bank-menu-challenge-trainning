package g.sants.microservices_communication.application.port.input;

import g.sants.microservices_communication.domain.Account;
import g.sants.microservices_communication.domain.User;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

@Component
public class Menus {

    private final User user = new User();
    private final Account account = new Account();
    static Scanner scanner = new Scanner(in);

    private void menuSelectorBank(int selected) {
        if (selected == 1) {
            newUserMenu();
        }
        if (selected == 2) {
            userMenu();
        }
    }

    private void menuSelectorUser(int selected) {
        if (selected == 1) {
            accData();
        }
        if (selected == 2) {
            userMenu();
        }
        if (selected == 3) {}
        if (selected == 4) {}
        if (selected == 5) {}
        if (selected == 6) {
            exitingMenu();
        }
    }

    private void accData() {
        user.accUserData();
        account.accAccData();
    }

    public void startMenu() {
        while (true) {
            out.println("\n Welcome to the AppBank");
            out.println("1 - Create new Account");
            out.println("2 - Login");
            out.println("3 - Exit");

            out.println("\nPlease Type one of the Options");
            if (!scanner.hasNextInt()) {
                out.println("Please select one of the Options (Numbers Only)");
                scanner.next();
                continue;
            }

            int selection = scanner.nextInt();

            if (selection == 3) {
                out.println("Exiting the application. Goodbye!");
                break;
            }

            menuSelectorBank(selection);
        }
    }

    private void userMenu() {
        out.println("Type in your ID");
        long customerID = scanner.nextLong();
        while (true) {
            out.println("1 - Verify User Data");
            out.println("2 - Deposit");
            out.println("3 - Transfer");
            out.println("4 - Verify/Change Limit");
            out.println("5 - Verify Transaction History");
            out.println("6 - Exit");
            out.println("\nPlease Type one of the Options");

            if (!scanner.hasNextInt()) {
                out.println("Please select one of the Options (Numbers Only)");
                scanner.next();
                continue;
            }

            int selection = scanner.nextInt();
            menuSelectorUser(selection);
            break;
        }
    }

    private void exitingMenu() {
        out.println("Exiting...");
        startMenu();
    }

    private void newUserMenu() {
        long customerID;
        while (true) {
            out.print("Type your ID");

            if (!scanner.hasNextLong()) {
                out.println("Please select one of the Options (Numbers Only)");
                scanner.next();
                continue;
            }

            customerID = scanner.nextLong();
            boolean validation = user.idValidation(customerID);

            if (!validation) {
                out.println("Invalid ID. Please try again.");
                continue;
            }
            break;
        }
        String name = collectUserName();
        String lastName = collectUserLastName();
        String accType = collectAccType();
        String email = collectUserEmail();
        String password = user.getPassword();
        new User(customerID, password, name, lastName, email, accType);

        out.println("\nCongratulations");
        startMenu();
    }

    private String collectUserName() {
        String name;
        while (true) {
            scanner.nextLine();
            out.print("\nType your Name: ");
            name = scanner.nextLine();

            if (name == null) {
                out.println("Please Type your Name again");
                continue;
            }
            break;
        }
        return name;
    }

    private String collectUserLastName() {
        String lastName;
        while (true) {
            scanner.nextLine();
            out.print("\nType your Last Name: ");
            lastName = scanner.nextLine();

            if (lastName == null) {
                out.println("Please Type your Last Name again");
                continue;
            }
            break;
        }
        return lastName;
    }

    private String collectUserEmail() {
        String email;
        while (true) {
            scanner.nextLine();
            out.print("\nType your Email: ");
            email = scanner.nextLine();

            if (email == null) {
                out.println("Please Type your Email again");
                continue;
            }
            break;
        }
        return email;
    }

    private String collectAccType() {
        String accType = null;
        while (true) {
            out.println("\nChoose the type of account you want to create");
            out.println("1 - Checking");
            out.println("2 - Salary");
            out.println("3 - Savings");

            if (!scanner.hasNextInt()) {
                out.println("Please select one of the Options (Numbers Only)");
                scanner.next();
                continue;
            }

            int type = scanner.nextInt();

            if (type < 1 || type > 3) {
                out.println("Please select a valid Option");
                scanner.next();
                continue;
            }

            if (type == 1) {accType = "Checking";}
            if (type == 2) {accType = "Salary";}
            if (type == 3) {accType = "Savings";}

            break;
        }
        return accType;
    }
}