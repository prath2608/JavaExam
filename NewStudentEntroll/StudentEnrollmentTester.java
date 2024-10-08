package p1;


import java.io.*;
import java.time.LocalDate;
import java.util.*;



abstract class Person implements Serializable {
    private String name;
    private String email;

    // Constructor
    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email;
    }
}




class Student extends Person {
    private int studentId; // Student ID (Primary Key)
    private String course; // Course enrolled
    private LocalDate enrollmentDate; // Enrollment date of the student

    // Constructor chaining: Calls the constructor of the base class (Person)
    public Student(int studentId, String name, String email, String course, LocalDate enrollmentDate) {
        super(name, email); // Calling the constructor of the base class
        this.studentId = studentId;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
    }

    // Getters for accessing student attributes
    public int getStudentId() {
        return studentId;
    }

    public String getCourse() {
        return course;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    // Overridden toString method for polymorphism
    @Override
    public String toString() {
        return "Student ID: " + studentId + ", " + super.toString() + ", Course: " + course + ", Enrollment Date: " + enrollmentDate;
    }
}


class StudentException extends Exception {
    public StudentException(String message) {
        super(message);
    }
}



class StudentCollectionUtils {

    // Method to populate the collection with sample student data
    public static Map<Integer, Student> initializeStudentCollection() {
        Map<Integer, Student> studentMap = new HashMap<>();
        studentMap.put(201, new Student(201, "Alice", "alice@university.com", "Computer Science", LocalDate.of(2021, 9, 1)));
        studentMap.put(202, new Student(202, "Bob", "bob@university.com", "Electrical Engineering", LocalDate.of(2020, 8, 20)));
        studentMap.put(203, new Student(203, "Charlie", "charlie@university.com", "Mechanical Engineering", LocalDate.of(2019, 7, 15)));
        studentMap.put(204, new Student(204, "David", "david@university.com", "Computer Science", LocalDate.of(2022, 1, 10)));
        studentMap.put(205, new Student(205, "Eve", "eve@university.com", "Physics", LocalDate.of(2022, 5, 5)));
        return studentMap;
    }
}



class StudentIOUtils {

    // Method to serialize the student collection to a file
    public static void saveStudentCollection(Map<Integer, Student> studentMap, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(studentMap);
        }
    }

    // Method to deserialize the student collection from a file
    public static Map<Integer, Student> loadStudentCollection(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Map<Integer, Student>) ois.readObject();
        }
    }
}



public class StudentEnrollmentTester {

    public static void main(String[] args) {
        Map<Integer, Student> studentMap = StudentCollectionUtils.initializeStudentCollection();
        Scanner scanner = new Scanner(System.in);

        String filePath = "studentData.dat"; // File path for saving student data

        while (true) {
            System.out.println("\nStudent Enrollment Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Save Student Collection to File");
            System.out.println("4. Load Student Collection from File");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        // Add a new student to the collection
                        System.out.print("Enter Student ID: ");
                        int studentId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        if (studentMap.containsKey(studentId)) {
                            throw new StudentException("Student with this ID already exists.");
                        }
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter Course: ");
                        String course = scanner.nextLine();
                        System.out.print("Enter Enrollment Date (yyyy-mm-dd): ");
                        LocalDate enrollmentDate = LocalDate.parse(scanner.nextLine());
                        Student newStudent = new Student(studentId, name, email, course, enrollmentDate);
                        studentMap.put(studentId, newStudent);
                        System.out.println("Student added successfully!");
                        break;

                    case 2:
                        // Display all students in the collection
                        System.out.println("\nAvailable Students:");
                        // Demonstrates polymorphism by calling the toString method from the derived class
                        studentMap.values().forEach(System.out::println);
                        break;

                    case 3:
                        // Save student collection to file
                        StudentIOUtils.saveStudentCollection(studentMap, filePath);
                        System.out.println("Students saved to file successfully!");
                        break;

                    case 4:
                        // Load student collection from file
                        studentMap = StudentIOUtils.loadStudentCollection(filePath);
                        System.out.println("Students loaded from file successfully!");
                        break;

                    case 0:
                        // Exit the application
                        System.out.println("Exiting Student Enrollment System. Goodbye!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (StudentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("I/O Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }
    }
}

