package org.example;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    private static final TransactionDao DAO = new TransactionDao();
    public static final String TRANSACTION_INCOME = "przychod";
    public static final String TRANSACTION_OUTCOME ="wydatek";
    private static Scanner scanner = new Scanner(System.in);

    public static void main( String[] args ) {

        while(true) {
            System.out.println("Wybierz opcję:");
            System.out.println("1 - dodaj");
            System.out.println("2 - modyfikuj");
            System.out.println("3 - usuń");
            System.out.println("4 - lista przychodów");
            System.out.println("5 - lista wydatków");
            System.out.println("0 - wyjście z programu");
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    create();
                    break;
                case "2":
                    update();
                    break;
                case "3":
                    deleteById();
                    break;
                case "4":
                    displayIncome();
                    break;
                case "5":
                    displayOutcome();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Niepoprawny wybór");
            }
        }


    }

    private static void create(){

        String type;
        do{
            System.out.println("Podaj typ: wydatek, przychod");
            type = scanner.nextLine();
        } while(!type.equals(TRANSACTION_OUTCOME) && !type.equals(TRANSACTION_INCOME));


        System.out.println("Podaj opis:");
        String description = scanner.nextLine();

        System.out.println("Podaj cenę:");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Podaj datę w formacie yyyy-mm-dd:");
        String date = scanner.nextLine();

        Transaction transaction = new Transaction(type, description, amount, date);
        DAO.save(transaction);
    }

    private static void update() {

        System.out.println("Podaj id książki którą chcesz zaktualizować");
        int id = scanner.nextInt();
        scanner.nextLine();

        String type;
        do{
            System.out.println("Podaj typ: wydatek, przychod");
            type = scanner.nextLine();
        } while(!type.equals(TRANSACTION_OUTCOME) && !type.equals(TRANSACTION_INCOME));


        System.out.println("Podaj opis:");
        String description = scanner.nextLine();

        System.out.println("Podaj cenę:");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Podaj datę w formacie yyyy-mm-dd:");
        String date = scanner.nextLine();

        Transaction transaction = new Transaction(id, type, description, amount, date);
        DAO.update(transaction);
    }

    private static void deleteById() {
        System.out.println("Podaj id ");
        int id = scanner.nextInt();
        DAO.deleteById(id);
        System.out.println("Rekord został usunięty");
    }

    private static  void displayIncome() {
        System.out.println(DAO.findByType(TRANSACTION_INCOME));
    }

    private static void displayOutcome() {
        System.out.println(DAO.findByType(TRANSACTION_OUTCOME));
    }
}
