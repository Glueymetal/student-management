package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentAppTest {
    @Test
    void testAddAndViewStudent() {
        StudentApp app = new StudentApp();
        app.addStudent(1, "Alice");
        assertEquals("Alice", app.viewStudent(1));
    }
}