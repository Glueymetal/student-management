package com.example;

import java.util.*;

public class StudentApp {
    private static Map<Integer, String> students = new HashMap<>();

    public void addStudent(int id, String name) {
        students.put(id, name);
    }

    public String viewStudent(int id) {
        return students.getOrDefault(id, "Not found");
    }

    public static void main(String[] args) {
        StudentApp app = new StudentApp();
        app.addStudent(1, "Alice");
        app.addStudent(2, "Bob");
        System.out.println("Student 1: " + app.viewStudent(1));
        System.out.println("Student 2: " + app.viewStudent(2));
        System.out.println("Student Management App running...");
    }
}