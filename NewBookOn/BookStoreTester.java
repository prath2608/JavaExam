package p1;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.*;
import java.io.*;



enum BookCategory {
    FICTION, NON_FICTION, COMICS, TECHNOLOGY, EDUCATION
}

// BookInfo class representing a book in the bookshop
class BookInfo implements Serializable {
    private String title; // Title of the book (Primary Key)
    private String author; // Author of the book
    private double price; // Price of the book
    private BookCategory category; // Category of the book
    private LocalDate publishDate; // Publish date of the book

    // Constructor to initialize the attributes
    public BookInfo(String title, String author, double price, BookCategory category, LocalDate publishDate) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.category = category;
        this.publishDate = publishDate;
    }

    // Getters for accessing book attributes
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public BookCategory getCategory() {
        return category;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", publishDate=" + publishDate +
                '}';
    }
}



class BookException extends Exception {
    public BookException(String message) {
        super(message);
    }
}


class BookCollectionUtils {

    // Method to populate the collection with sample book data
    public static Map<String, BookInfo> initializeBookCollection() {
        Map<String, BookInfo> bookMap = new HashMap<>();
        bookMap.put("Book1", new BookInfo("Book1", "Author1", 500, BookCategory.FICTION, LocalDate.of(2020, 1, 10)));
        bookMap.put("Book2", new BookInfo("Book2", "Author2", 300, BookCategory.NON_FICTION, LocalDate.of(2021, 3, 5)));
        bookMap.put("Book3", new BookInfo("Book3", "Author3", 150, BookCategory.COMICS, LocalDate.of(2019, 5, 20)));
        bookMap.put("Book4", new BookInfo("Book4", "Author4", 600, BookCategory.TECHNOLOGY, LocalDate.of(2022, 8, 15)));
        bookMap.put("Book5", new BookInfo("Book5", "Author5", 450, BookCategory.EDUCATION, LocalDate.of(2023, 2, 18)));
        return bookMap;
    }
}

class BookIOUtils {

    // Method to serialize the book collection to a file
    public static void saveBookCollection(Map<String, BookInfo> bookMap, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(bookMap);
        }
    }

    // Method to deserialize the book collection from a file
    public static Map<String, BookInfo> loadBookCollection(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Map<String, BookInfo>) ois.readObject();
        }
    }
}


public class BookStoreTester {

    public static void main(String[] args) {
        // Initialize the book collection with sample data
        Map<String, BookInfo> bookMap = BookCollectionUtils.initializeBookCollection();
        Scanner scanner = new Scanner(System.in);

        String filePath = "bookStoreData.dat"; // File path for saving book data

        while (true) {
            System.out.println("\nBookStore Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Save Book Collection to File");
            System.out.println("4. Load Book Collection from File");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        // Add a new book to the collection
                        System.out.print("Enter Book Title: ");
                        String title = scanner.nextLine();
                        if (bookMap.containsKey(title)) {
                            throw new BookException("Book with this title already exists.");
                        }
                        System.out.print("Enter Author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter Price: ");
                        double price = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter Category (FICTION, NON_FICTION, COMICS, TECHNOLOGY, EDUCATION): ");
                        BookCategory category = BookCategory.valueOf(scanner.nextLine().toUpperCase());
                        System.out.print("Enter Publish Date (yyyy-mm-dd): ");
                        LocalDate publishDate = LocalDate.parse(scanner.nextLine());
                        BookInfo newBook = new BookInfo(title, author, price, category, publishDate);
                        bookMap.put(title, newBook);
                        System.out.println("Book added successfully!");
                        break;

                    case 2:
                        // Display all books in the collection
                        System.out.println("\nAvailable Books:");
                        bookMap.values().forEach(System.out::println);
                        break;

                    case 3:
                        // Save book collection to file
                        BookIOUtils.saveBookCollection(bookMap, filePath);
                        System.out.println("Books saved to file successfully!");
                        break;

                    case 4:
                        // Load book collection from file
                        bookMap = BookIOUtils.loadBookCollection(filePath);
                        System.out.println("Books loaded from file successfully!");
                        break;

                    case 0:
                        // Exit the application
                        System.out.println("Exiting BookStore. Goodbye!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (BookException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("I/O Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid category or date format. Please try again.");
            }
        }
    }
}
