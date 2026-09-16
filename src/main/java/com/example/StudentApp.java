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
    String method = exchange.getRequestMethod();

    if ("GET".equals(method)) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String> entry : students.entrySet()) {
            sb.append("Student ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        response = sb.length() == 0 ? "No students yet" : sb.toString();
    } else if ("POST".equals(method)) {
        String query = exchange.getRequestURI().getQuery(); // e.g. id=3&name=Charlie
        Map<String, String> params = new HashMap<>();
        if (query != null) {
            for (String pair : query.split("&")) {
                String[] kv = pair.split("=");
                if (kv.length == 2) params.put(kv[0], kv[1]);
            }
        }
        try {
            int id = Integer.parseInt(params.get("id"));
            String name = params.get("name");
            app.addStudent(id, name);
            response = "Added: " + id + " -> " + name;
        } catch (Exception e) {
            response = "Invalid input. Use POST /students?id=3&name=Charlie";
        }
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