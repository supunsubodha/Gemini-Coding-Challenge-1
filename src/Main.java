import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Welcome to the secure terminal program---");
        System.out.print("Please enter your username: ");
        String username = sc.nextLine();
        boolean login;
        int attempts = 0;
        do {
            System.out.print("Please enter your password: ");
            String password = PasswordHash.generateSHA256Hash(sc.nextLine());
            if (attempts != 0){
                System.out.println("Incorrect username or password. Please try again.");
            } else if (attempts>5) {
                System.out.println("Program will be suspend for security reasons.");
                return;
            }
            login=DatabaseConnecter.verifyLogin(username, password);
            attempts++;
        }while (login==false);
        System.out.println("Your password has been verified");
    }
}