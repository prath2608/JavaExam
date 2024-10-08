package p1;


import java.io.*;
import java.util.*;



abstract class Vehicle implements Serializable {
    private String licensePlate; // Unique identifier for the vehicle
    private String brand; // Brand of the vehicle
    private String model; // Model of the vehicle
    private double dailyRentalPrice; // Rental price per day

    // Constructor
    public Vehicle(String licensePlate, String brand, String model, double dailyRentalPrice) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.dailyRentalPrice = dailyRentalPrice;
    }

    // Getters and setters
    public String getLicensePlate() {
        return licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getDailyRentalPrice() {
        return dailyRentalPrice;
    }

    @Override
    public String toString() {
        return "License Plate: " + licensePlate + ", Brand: " + brand + ", Model: " + model + ", Daily Rental Price: $" + dailyRentalPrice;
    }
}


class Car extends Vehicle {
    private String fuelType; // Fuel type of the car (e.g., Petrol, Diesel, Electric)

    // Constructor chaining: Calls the constructor of the base class (Vehicle)
    public Car(String licensePlate, String brand, String model, double dailyRentalPrice, String fuelType) {
        super(licensePlate, brand, model, dailyRentalPrice); // Calling the constructor of the base class
        this.fuelType = fuelType;
    }

    // Getter for fuel type
    public String getFuelType() {
        return fuelType;
    }

    // Overridden toString method for polymorphism
    @Override
    public String toString() {
        return super.toString() + ", Fuel Type: " + fuelType;
    }
}



class Truck extends Vehicle {
    private double loadCapacity; // Load capacity of the truck in tons

    // Constructor chaining: Calls the constructor of the base class (Vehicle)
    public Truck(String licensePlate, String brand, String model, double dailyRentalPrice, double loadCapacity) {
        super(licensePlate, brand, model, dailyRentalPrice); // Calling the constructor of the base class
        this.loadCapacity = loadCapacity;
    }

    // Getter for load capacity
    public double getLoadCapacity() {
        return loadCapacity;
    }

    // Overridden toString method for polymorphism
    @Override
    public String toString() {
        return super.toString() + ", Load Capacity: " + loadCapacity + " tons";
    }
}


class RentalException extends Exception {
    public RentalException(String message) {
        super(message);
    }
}



class VehicleCollectionUtils {

    // Method to populate the collection with sample vehicle data
    public static Map<String, Vehicle> initializeVehicleCollection() {
        Map<String, Vehicle> vehicleMap = new HashMap<>();
        vehicleMap.put("ABC123", new Car("ABC123", "Toyota", "Corolla", 30.00, "Petrol"));
        vehicleMap.put("XYZ789", new Truck("XYZ789", "Ford", "F-150", 50.00, 2.5));
        vehicleMap.put("DEF456", new Car("DEF456", "Honda", "Civic", 28.00, "Diesel"));
        vehicleMap.put("GHI101", new Truck("GHI101", "Mercedes", "Actros", 80.00, 5.0));
        vehicleMap.put("JKL321", new Car("JKL321", "Tesla", "Model S", 100.00, "Electric"));
        return vehicleMap;
    }
}


class VehicleIOUtils {

    // Method to serialize the vehicle collection to a file
    public static void saveVehicleCollection(Map<String, Vehicle> vehicleMap, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(vehicleMap);
        }
    }

    // Method to deserialize the vehicle collection from a file
    public static Map<String, Vehicle> loadVehicleCollection(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Map<String, Vehicle>) ois.readObject();
        }
    }
}



public class CarRentalSystem {

    public static void main(String[] args) {
        Map<String, Vehicle> vehicleMap = VehicleCollectionUtils.initializeVehicleCollection();
        Scanner scanner = new Scanner(System.in);

        String filePath = "vehicleData.dat"; // File path for saving vehicle data

        while (true) {
            System.out.println("\nCar Rental System Menu:");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Display All Vehicles");
            System.out.println("3. Save Vehicle Collection to File");
            System.out.println("4. Load Vehicle Collection from File");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        // Add a new vehicle to the collection
                        System.out.print("Enter License Plate: ");
                        String licensePlate = scanner.nextLine();
                        if (vehicleMap.containsKey(licensePlate)) {
                            throw new RentalException("Vehicle with this license plate already exists.");
                        }
                        System.out.print("Enter Brand: ");
                        String brand = scanner.nextLine();
                        System.out.print("Enter Model: ");
                        String model = scanner.nextLine();
                        System.out.print("Enter Daily Rental Price: ");
                        double dailyRentalPrice = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter Vehicle Type (Car/Truck): ");
                        String vehicleType = scanner.nextLine();

                        // Create the appropriate vehicle object based on vehicle type
                        Vehicle newVehicle;
                        if (vehicleType.equalsIgnoreCase("Car")) {
                            System.out.print("Enter Fuel Type: ");
                            String fuelType = scanner.nextLine();
                            newVehicle = new Car(licensePlate, brand, model, dailyRentalPrice, fuelType);
                        } else if (vehicleType.equalsIgnoreCase("Truck")) {
                            System.out.print("Enter Load Capacity (tons): ");
                            double loadCapacity = scanner.nextDouble();
                            newVehicle = new Truck(licensePlate, brand, model, dailyRentalPrice, loadCapacity);
                        } else {
                            throw new RentalException("Invalid vehicle type. Please enter 'Car' or 'Truck'.");
                        }

                        vehicleMap.put(licensePlate, newVehicle);
                        System.out.println("Vehicle added successfully!");
                        break;

                    case 2:
                        // Display all vehicles in the collection
                        System.out.println("\nAvailable Vehicles:");
                        // Demonstrates polymorphism by calling the toString method from derived classes
                        vehicleMap.values().forEach(System.out::println);
                        break;

                    case 3:
                        // Save vehicle collection to file
                        VehicleIOUtils.saveVehicleCollection(vehicleMap, filePath);
                        System.out.println("Vehicles saved to file successfully!");
                        break;

                    case 4:
                        // Load vehicle collection from file
                        vehicleMap = VehicleIOUtils.loadVehicleCollection(filePath);
                        System.out.println("Vehicles loaded from file successfully!");
                        break;

                    case 0:
                        // Exit the application
                        System.out.println("Exiting Car Rental System. Goodbye!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (RentalException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("I/O Error: " + e.getMessage());
            }
        }
    }
}

