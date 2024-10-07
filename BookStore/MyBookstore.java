package book.shop;

import java.util.Scanner;
import java.util.ArrayList;
public class MyBookstore {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    Bookstore myBkStore = new Bookstore();
    Book book = new Book();
    String title = s.nextLine();

    int y = 0;
    int user_choice = 2;
    boolean quit = false;

    do {
        //display menu to user
        //ask user for his choice and validate it (make sure it is between 1 and 6)
        System.out.println();
        System.out.println("1) Add a book to the stock");
        System.out.println("2) Sell a book in stock");
        System.out.println("3) List the titles of all the books in stock (in the Bookstore object)");
        System.out.println("4) List all the information about the books in stock (in the Bookstore object)");
        System.out.println("5) Print out the gross income of the bookstore");
        System.out.println("6) Quit");
        System.out.println();
        System.out.print("Enter choice [1-6]: ");
        user_choice = s.nextInt();
        
		switch (user_choice) {
            case 1: System.out.println("Enter a book title");


                    String bt = s.next();           //stores title of book user enters in

                    if (myBkStore.inStock(title, y))
                    {
                        System.out.println("How many more to add to the stock");
                        y = s.nextInt();
                        myBkStore.addBookQuantity(bt, y);
                    }
                    else
                    {
                        System.out.println("Enter the amount of pages of the book: ");
                        int pages = s.nextInt();
                        System.out.println("Enter the price of the book: ");
                        double price = s.nextDouble();
                        System.out.println("Enter the quantity to add: ");
                        int quant = s.nextInt();
                        //myBkStore.Book(bt, pages, price, quant);
                        myBkStore.addNewBook(book);
                    } 


                    break;
            case 2: System.out.println("Enter book title to buy: ");
                    String bookT = s.next();
                    myBkStore.sellBook(title, y);
                    break;
            case 3: myBkStore.listTitles();
                    break;
            case 4: myBkStore.listBooks();
                    break;
            case 5: myBkStore.getIncome();
                    break;
            case 6: System.out.println("Thanks for coming");
                    quit = true;
                    break;
            default: System.out.println("\nInvalid Choice");
        }
}
while (!quit);
}

static class Bookstore {
private Book[] books; // all the books in this bookstore
private int totalBooks; // the number of books in this bookstore
    private double grossIncome; //the gross income of the bookstore (will be incremented when books are sold)

// Constructor: A new Bank object initially doesn’t contain any accounts.
public Bookstore() {
    books = new Book[100];
    totalBooks = 0;
    grossIncome = 0;
    }

// Creates a new bank account using the customer name and the opening balance given as parameters
// and returns the account number of this new account. It also adds this account into the account list
// of the Bank calling object.
public void addNewBook(Book b) {
    if(totalBooks < books.length) {
        books[totalBooks] = b;
        totalBooks++;

    }
    else
    {
        System.out.println("\nSorry, cannot add book to stock.");
    }


}


public void addBookQuantity(String title, int quantity) {
    for (int i =0; i<totalBooks; i++) {
        if (title == books[i].getTitle()  ) {
            books[i].addQuantity(quantity);
            System.out.println("Quantity added successfully");
            return;
        }
    }

    /*int i;

    for (i = 0; i < totalbooks; i++)
    {
        if((books[i].getTitle()).equals(title))
        {
            books[i].addQuantity(quantity);
            return;
        }
    }*/


}

public boolean inStock (String title, int quantity) {
    for (int i =0; i<totalBooks; i++) {
            if (title.equals(books[i].getTitle())) {
                if (quantity <= books[i].getQuantity()) {return true;}
                else {return false;}
            }
        }
    return false;

}

public boolean sellBook(String title, int quantity){
    int i;

        boolean sellflag=false;

        // Checks to see if the books are in stock.

        boolean retval = inStock(title, quantity);

        // If so, completes the sale.

        if (retval) {

          for (i=0; i<totalBooks && !sellflag; i++) {

            if (title.equals(books[i].getTitle())) {

              books[i].subtractQuantity(quantity);

              grossIncome += (books[i].getPrice()) * quantity;

              sellflag = true;

            }

          } // for

        } // if

        return retval;

      } // sellBook






public void listTitles()
{
    for (int i = 0; i < totalBooks; i++)
    {
        System.out.println(books[i].getTitle());
    }

}

public void listBooks()
{
    int i;

    System.out.println("\nList of Books\n======");
    for (i = 0; i < totalBooks; i++)
    {
        System.out.println(books[i]);
    }
    System.out.println();
}

public double getIncome()
{
    return grossIncome;
}





}





  static class Book{

       private String title;
       private int numOfPages;
       private double price;
       private int quantity;
       private Book book;

       public String toString(){
           return "Title: " + title + "\nNumber of pages: " + numOfPages + "\nPrice:" + price +"\nQuantity: " + quantity + "\n";


       }


       public Book book(String thetitle, int pages, double cost, int num){
         /*title = thetitle;
         numOfPages = pages;
         price = cost;
         quantity = num;*/
         return book;

       }

       public String getTitle(){
         return title;
       }

       public double getPrice(){
         return price;
       }

       public int getQuantity(){
         return quantity;
       }

       public void addQuantity(int amount){
            quantity = quantity + amount;

    }
    public void subtractQuantity(int amount)
    {
          System.out.println("Amount to buy");
        Scanner s = new Scanner(System.in);
        quantity = s.nextInt();
        quantity = quantity - amount;   
    }

}//end of class
}