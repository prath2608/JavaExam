package p1;

import java.io.*;
import java.time.LocalDate;
import java.util.*;


class Employee implements Serializable {
    private int empId; // Employee ID (Primary Key)
    private String name; // Employee name
    private String department; // Department of the employee
    private double salary; // Employee salary
    private LocalDate hireDate; // Hire date of the employee

    // Constructor to initialize the employee attributes
    public Employee(int empId, String name, String department, double salary, LocalDate hireDate) {
        this.empId = empId;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    // Getters for accessing employee attributes
    public int getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                '}';
    }
}



class EmployeeException extends Exception {
    public EmployeeException(String message) {
        super(message);
    }
}

class EmployeeCollectionUtils {

    // Method to populate the collection with sample employee data
    public static Map<Integer, Employee> initializeEmployeeCollection() {
        Map<Integer, Employee> employeeMap = new HashMap<>();
        employeeMap.put(101, new Employee(101, "Alice", "HR", 50000, LocalDate.of(2021, 3, 15)));
        employeeMap.put(102, new Employee(102, "Bob", "Finance", 60000, LocalDate.of(2020, 6, 10)));
        employeeMap.put(103, new Employee(103, "Charlie", "IT", 75000, LocalDate.of(2019, 9, 25)));
        employeeMap.put(104, new Employee(104, "David", "IT", 80000, LocalDate.of(2022, 1, 5)));
        employeeMap.put(105, new Employee(105, "Eve", "Sales", 45000, LocalDate.of(2018, 7, 30)));
        return employeeMap;
    }
}




class EmployeeIOUtils {

    // Method to serialize the employee collection to a file
    public static void saveEmployeeCollection(Map<Integer, Employee> employeeMap, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(employeeMap);
        }
    }

    // Method to deserialize the employee collection from a file
    public static Map<Integer, Employee> loadEmployeeCollection(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Map<Integer, Employee>) ois.readObject();
        }
    }
}



public class EmployeeManagementTester {

    public static void main(String[] args) {
        Map<Integer, Employee> employeeMap = EmployeeCollectionUtils.initializeEmployeeCollection();
        Scanner scanner = new Scanner(System.in);

        String filePath = "employeeData.dat"; // File path for saving employee data

        while (true) {
            System.out.println("\nEmployee Management Menu:");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Save Employee Collection to File");
            System.out.println("4. Load Employee Collection from File");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        // Add a new employee to the collection
                        System.out.print("Enter Employee ID: ");
                        int empId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        if (employeeMap.containsKey(empId)) {
                            throw new EmployeeException("Employee with this ID already exists.");
                        }
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Department: ");
                        String department = scanner.nextLine();
                        System.out.print("Enter Salary: ");
                        double salary = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter Hire Date (yyyy-mm-dd): ");
                        LocalDate hireDate = LocalDate.parse(scanner.nextLine());
                        Employee newEmployee = new Employee(empId, name, department, salary, hireDate);
                        employeeMap.put(empId, newEmployee);
                        System.out.println("Employee added successfully!");
                        break;

                    case 2:
                        // Display all employees in the collection
                        System.out.println("\nAvailable Employees:");
                        employeeMap.values().forEach(System.out::println);
                        break;

                    case 3:
                        // Save employee collection to file
                        EmployeeIOUtils.saveEmployeeCollection(employeeMap, filePath);
                        System.out.println("Employees saved to file successfully!");
                        break;

                    case 4:
                        // Load employee collection from file
                        employeeMap = EmployeeIOUtils.loadEmployeeCollection(filePath);
                        System.out.println("Employees loaded from file successfully!");
                        break;

                    case 0:
                        // Exit the application
                        System.out.println("Exiting Employee Management System. Goodbye!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (EmployeeException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("I/O Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }
    }
}

