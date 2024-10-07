package p1;

import java.util.ArrayList;

//Define the Restaurant class
class Restaurant {
// Declare ArrayLists to store menu items, prices, ratings, and item counts
private ArrayList menuItems;
private ArrayList prices;
private ArrayList ratings;
private ArrayList itemCounts;

// Constructor to initialize the ArrayLists
public Restaurant() {
 // Initialize the menuItems ArrayList
 this.menuItems = new ArrayList();
 // Initialize the prices ArrayList
 this.prices = new ArrayList();
 // Initialize the ratings ArrayList
 this.ratings = new ArrayList();
 // Initialize the itemCounts ArrayList
 this.itemCounts = new ArrayList();
}

// Method to add an item to the menu
public void addItem(String item, double price) {
 // Add the item to the menuItems ArrayList
 this.menuItems.add(item);
 // Add the price to the prices ArrayList
 this.prices.add(price);
 // Initialize the rating for the item to 0
 this.ratings.add(0);
 // Initialize the item count for the item to 0
 this.itemCounts.add(0);
}

// Method to remove an item from the menu
public void removeItem(String item) {
 // Get the index of the item in the menuItems ArrayList
 int index = this.menuItems.indexOf(item);
 // If the item exists in the menu
 if (index >= 0) {
   // Remove the item from the menuItems ArrayList
   this.menuItems.remove(index);
   // Remove the corresponding price from the prices ArrayList
   this.prices.remove(index);
   // Remove the corresponding rating from the ratings ArrayList
   this.ratings.remove(index);
   // Remove the corresponding item count from the itemCounts ArrayList
   this.itemCounts.remove(index);
 }
}

// Method to add a rating to an item
public void addRating(String item, int rating) {
 // Get the index of the item in the menuItems ArrayList
 int index = this.menuItems.indexOf(item);
 // If the item exists in the menu
 if (index >= 0) {
   // Get the current rating of the item
   int currentRating = (int) this.ratings.get(index);
   // Get the current item count of the item
   int totalCount = (int) this.itemCounts.get(index);
   // Update the rating of the item
   this.ratings.set(index, currentRating + rating);
   // Update the item count of the item
   this.itemCounts.set(index, totalCount + 1);
 }
}

// Method to get the average rating of an item
public double getAverageRating(String item) {
 // Get the index of the item in the menuItems ArrayList
 int index = this.menuItems.indexOf(item);
 // If the item exists in the menu
 if (index >= 0) {
   // Get the total rating of the item
   int totalRating = (int) this.ratings.get(index);
   // Get the item count of the item
   int itemCount = (int) this.itemCounts.get(index);
   // Calculate and return the average rating of the item
   return itemCount > 0 ? (double) totalRating / itemCount : 0.0;
 } else {
   // Return 0.0 if the item does not exist in the menu
   return 0.0;
 }
}

// Method to display the menu
public void displayMenu() {
 // Loop through the menuItems ArrayList
 for (int i = 0; i < menuItems.size(); i++) {
   // Print the item and its price
   System.out.println(menuItems.get(i) + ": $" + prices.get(i));
 }
}

// Method to calculate the average rating of all items
public double calculateAverageRating() {
 // Initialize totalRating to 0
 double totalRating = 0;
 // Initialize numRatings to 0
 int numRatings = 0;
 // Loop through the ratings ArrayList
 for (int i = 0; i < ratings.size(); i++) {
   // Add the rating to totalRating
   totalRating += (int) ratings.get(i);
   // Increment numRatings
   numRatings++;
 }
 // Calculate and return the average rating
 return numRatings > 0 ? totalRating / numRatings : 0.0;
}
}

public class RestaurantMain {
	  // Main method, the entry point of the application
	  public static void main(String[] args) {
	    // Create a new Restaurant object
	    Restaurant restaurant = new Restaurant();
	    // Add a Burger item with a price of $8.99 to the menu
	    restaurant.addItem("Burger", 8.99);
	    // Add a Pizza item with a price of $10.99 to the menu
	    restaurant.addItem("Pizza", 10.99);
	    // Add a Salad item with a price of $6.00 to the menu
	    restaurant.addItem("Salad", 6.00);

	    // Print the menu header
	    System.out.println("Menu: Item & Price");
	    // Display the menu items and their prices
	    restaurant.displayMenu();

	    // Add a rating of 4 to the Burger item
	    restaurant.addRating("Burger", 4);
	    // Add a rating of 5 to the Burger item
	    restaurant.addRating("Burger", 5);
	    // Add a rating of 3 to the Pizza item
	    restaurant.addRating("Pizza", 3);
	    // Add a rating of 4 to the Pizza item
	    restaurant.addRating("Pizza", 4);
	    // Add a rating of 2 to the Salad item
	    restaurant.addRating("Salad", 2);

	    // Get the average rating for the Burger item
	    double averageRating = restaurant.getAverageRating("Burger");
	    // Print the average rating for the Burger item
	    System.out.println("\nAverage rating for Burger: " + averageRating);
	    // Get the average rating for the Pizza item
	    averageRating = restaurant.getAverageRating("Pizza");
	    // Print the average rating for the Pizza item
	    System.out.println("Average rating for Pizza: " + averageRating);
	    // Get the average rating for the Salad item
	    averageRating = restaurant.getAverageRating("Salad");
	    // Print the average rating for the Salad item
	    System.out.println("Average rating for Salad: " + averageRating);
	    // Print the overall average rating for all items
	    System.out.println("Average rating: " + restaurant.calculateAverageRating());

	    // Print a message indicating that the Pizza item will be removed
	    System.out.println("\nRemove 'Pizza' from the above menu.");
	    // Remove the Pizza item from the menu
	    restaurant.removeItem("Pizza");
	    // Print the updated menu header
	    System.out.println("\nUpdated menu:");
	    // Display the updated menu items and their prices
	    restaurant.displayMenu();
	  }
	}