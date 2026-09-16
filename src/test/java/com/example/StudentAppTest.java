package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentAppTest {
    @Test
    void testAddAndViewStudent() {
        StudentApp app = new StudentApp();
        app.addStudent(1, "Alice");
        assertEquals("Alice", app.viewStudent(1));
        assertEquals("Not found", app.viewStudent(99));
    }
}