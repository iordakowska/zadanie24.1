package org.example;

import java.util.List;
import java.util.Scanner;

public class App {

    public static final String OPTION_INSERT = "1";
    public static final String OPTION_UPDATE = "2";
    public static final String OPTION_DELETE = "3";
    public static final String OPTION_DISPLAY_INCOME = "4";
    public static final String OPTION_DISPLAY_OUTCOM = "5";
    public static final String EXIT = "0";
    public static final String TRANSACTION_INCOME = "przychod";
    public static final String TRANSACTION_OUTCOME = "wydatek";
    private static Scanner scanner = new Scanner(System.in);

    private final TransactionDao dao = new TransactionDao();

    public static void main(String[] args) {
        
        App app = new App();
        app.run();
    }

    private void run() {
        while (true) {
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
                case OPTION_INSERT -> create();
                case OPTION_UPDATE -> update();
                case OPTION_DELETE -> deleteById();
                case OPTION_DISPLAY_INCOME -> displayIncome();
                case OPTION_DISPLAY_OUTCOM -> displayOutcome();
                case EXIT -> {
                    return; }
                default -> System.out.println("Niepoprawny wybór");
            }
        }
    }

    private void create() {

        String type;
        do {
            System.out.println("Podaj typ: wydatek, przychod");
            type = scanner.nextLine();
        } while (!type.equals(TRANSACTION_OUTCOME) && !type.equals(TRANSACTION_INCOME));

        System.out.println("Podaj opis:");
        String description = scanner.nextLine();

        System.out.println("Podaj cenę:");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Podaj datę w formacie yyyy-mm-dd:");
        String date = scanner.nextLine();

        Transaction transaction = new Transaction(type, description, amount, date);
        dao.save(transaction);
    }

    private void update() {

        System.out.println("Podaj id transakcji, którą chcesz zaktualizować");
        int id = scanner.nextInt();
        scanner.nextLine();

        String type;
        do {
            System.out.println("Podaj typ: wydatek, przychod");
            type = scanner.nextLine();
        } while (!type.equals(TRANSACTION_OUTCOME) && !type.equals(TRANSACTION_INCOME));

        System.out.println("Podaj opis:");
        String description = scanner.nextLine();

        System.out.println("Podaj cenę:");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Podaj datę w formacie yyyy-mm-dd:");
        String date = scanner.nextLine();

        Transaction transaction = new Transaction(id, type, description, amount, date);
        dao.update(transaction);
    }

    private void deleteById() {
        System.out.println("Podaj id ");
        int id = scanner.nextInt();
        dao.deleteById(id);
        System.out.println("Rekord został usunięty");
    }

    private void displayIncome() {
        List<Transaction> transactions = dao.findByType(TRANSACTION_INCOME);
        System.out.println("Lista przychodów:" + transactions.size());
        if (!(transactions == null || transactions.isEmpty())) {
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        } else {
            System.out.println("Brak przychodów \n");
        }
    }

    private void displayOutcome() {
        List<Transaction> transactions = dao.findByType(TRANSACTION_OUTCOME);
        System.out.println("Lista wydatków:" + transactions.size());
        if (!(transactions == null || transactions.isEmpty())) {
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        } else {
            System.out.println("Brak wydatków \n");
        }
    }
}