package b;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.List;

abstract class Item {
    private String title;
    private String author;
    private double price;

    public Item(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public abstract String getDetails();
}

class Book extends Item {
    private String isbn;

    public Book(String title, String author, double price, String isbn) {
        super(title, author, price); // Constructor chaining
        this.isbn = isbn;
    }

    @Override
    public String getDetails() {
        return "Book Title: " + getTitle() + ", Author: " + getAuthor() + ", Price: " + getPrice() + ", ISBN: " + isbn;
    }
}



class Magazine extends Item {
    private String issueNumber;

    public Magazine(String title, String author, double price, String issueNumber) {
        super(title, author, price); // Constructor chaining
        this.issueNumber = issueNumber;
    }

    @Override
    public String getDetails() {
        return "Magazine Title: " + getTitle() + ", Author: " + getAuthor() + ", Price: " + getPrice() + ", Issue Number: " + issueNumber;
    }
}

class LibraryException extends Exception {
    public LibraryException(String message) {
        super(message);
    }
}


class LibraryUtils {
    public static void displayItems(List<Item> items) {
        for (Item item : items) {
            System.out.println(item.getDetails());
        }
    }
}




public class LibraryManagement {
    private static List<Item> libraryItems = new ArrayList<>();

    public static void main(String[] args) {
        initializeLibrary();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Select an option:");
            System.out.println("1. Display all items");
            System.out.println("2. Add Book");
            System.out.println("3. Add Magazine");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    LibraryUtils.displayItems(libraryItems);
                    break;
                case 2:
                    try {
                        addBook(scanner);
                    } catch (LibraryException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        addMagazine(scanner);
                    } catch (LibraryException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    private static void initializeLibrary() {
        libraryItems.add(new Book("Effective Java", "Joshua Bloch", 45.0, "978-0134686097"));
        libraryItems.add(new Magazine("National Geographic", "Various", 5.0, "August 2024"));
    }

    private static void addBook(Scanner scanner) throws LibraryException {
        System.out.println("Enter book title:");
        String title = scanner.nextLine();
        System.out.println("Enter author:");
        String author = scanner.nextLine();
        System.out.println("Enter price:");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter ISBN:");
        String isbn = scanner.nextLine();

        if (title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
            throw new LibraryException("Book details cannot be empty.");
        }

        libraryItems.add(new Book(title, author, price, isbn));
        System.out.println("Book added successfully!");
    }

    private static void addMagazine(Scanner scanner) throws LibraryException {
        System.out.println("Enter magazine title:");
        String title = scanner.nextLine();
        System.out.println("Enter author:");
        String author = scanner.nextLine();
        System.out.println("Enter price:");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter issue number:");
        String issueNumber = scanner.nextLine();

        if (title.isEmpty() || author.isEmpty() || issueNumber.isEmpty()) {
            throw new LibraryException("Magazine details cannot be empty.");
        }

        libraryItems.add(new Magazine(title, author, price, issueNumber));
        System.out.println("Magazine added successfully!");
    }
}
