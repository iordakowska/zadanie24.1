package org.example;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    private static final TransactionDao DAO = new TransactionDao();

    public static void main( String[] args ) {
        create();
    }

    private static void create(){
        Scanner scanner = new Scanner(System.in);

        String type;
        do{
            System.out.println("Podaj typ: wydatek, przychod");
            type = scanner.nextLine();
        } while(!type.equals("wydatek") && !type.equals("przychod"));


        System.out.println("Podaj opis:");
        String description = scanner.nextLine();
        scanner.nextLine();

        System.out.println("Podaj cenę:");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Podaj datę w formacie yyyy-mm-dd:");
        String date = scanner.nextLine();

        Transaction transaction = new Transaction(type, description, amount, date);
        DAO.save(transaction);
    }
}
