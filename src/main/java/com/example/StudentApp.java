package com.example;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class StudentApp {
    private static Map<Integer, String> students = new HashMap<>();

    public void addStudent(int id, String name) {
        students.put(id, name);
    }

    public String viewStudent(int id) {
        return students.getOrDefault(id, "Not found");
    }

    public static void main(String[] args) throws IOException {
        StudentApp app = new StudentApp();
        app.addStudent(1, "Alice");
        app.addStudent(2, "Bob");

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/students", exchange -> {
            String response;
            if ("GET".equals(exchange.getRequestMethod())) {
                response = "Student 1: " + app.viewStudent(1) + "\nStudent 2: " + app.viewStudent(2);
            } else {
                response = "Method not supported";
            }
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });
        server.setExecutor(null);
        server.start();
        System.out.println("Student Management App running on port 8080...");
    }
}