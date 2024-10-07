package student.magmnent;


import java.io.*;

import java.util.*;

// Abstract class Student
abstract class Student implements Comparable<Student> {
    private int id;
    private String name;
    private double gpa;
    private String department;

    public Student(int id, String name, double gpa, String department) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
        this.department = department;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    public String getDepartment() {
        return department;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public int compareTo(Student other) {
        return Double.compare(other.gpa, this.gpa);
    }

    public abstract void updateGpa(double newGpa);
}

// Concrete subclass UndergraduateStudent
class UndergraduateStudent extends Student {
    public UndergraduateStudent(int id, String name, double gpa, String department) {
        super(id, name, gpa, department);
    }

    @Override
    public void updateGpa(double newGpa) {
        setGpa(newGpa);
    }
}

// StudentComparator for sorting by GPA in descending order
class StudentComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return Double.compare(s2.getGpa(), s1.getGpa());
    }
}

// StudentManagementSystem
public class StudentManagementSystem {
    private PriorityQueue<Student> studentQueue;
    private String fileName;

    public StudentManagementSystem(String fileName) {
        this.fileName = fileName;
        this.studentQueue = new PriorityQueue<>(new StudentComparator());
    }

    // Add student to the system
    public void addStudent(Student student) {
        studentQueue.add(student);
    }

    // Load students from file
    public void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double gpa = Double.parseDouble(parts[2]);
                String department = parts[3];
                UndergraduateStudent student = new UndergraduateStudent(id, name, gpa, department);
                addStudent(student);
            }
        } catch (IOException e) {
            System.out.println("Error loading students from file: " + e.getMessage());
        }
    }

    // Save students to file
    public void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Student student : studentQueue) {
                writer.write(student.getId() + "," + student.getName() + "," + student.getGpa() + "," + student.getDepartment());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving students to file: " + e.getMessage());
        }
    }

    // Display students
    public void displayStudents() {
        while (!studentQueue.isEmpty()) {
            Student student = studentQueue.poll();
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("GPA: " + student.getGpa());
            System.out.println("Department: " + student.getDepartment());
            System.out.println();
        }
    }

    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem("students.txt");

        // Add students
        UndergraduateStudent s1 = new UndergraduateStudent(1, "John Doe", 3.8, "Computer Science");
        UndergraduateStudent s2 = new UndergraduateStudent(2, "Jane Doe", 3.9, "Mathematics");
        UndergraduateStudent s3 = new UndergraduateStudent(3, "Bob Smith", 3.7, "Engineering");

        sms.addStudent(s1);
        sms.addStudent(s2);
        sms.addStudent(s3);

        // Save students to file
        sms.saveStudentsToFile();

        // Load students from file
        sms.loadStudentsFromFile();

        // Display students
        sms.displayStudents();
    }
}
