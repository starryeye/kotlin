package dev.starryeye.java_test;

import org.junit.jupiter.api.*;

public class JavaTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach
    void setUp() {
        System.out.println("before each");
    }

    @AfterEach
    void tearDown() {
        System.out.println("after each");
    }

    @Test
    void test1() {
        System.out.println("test1");
    }

    @Test
    void test2() {
        System.out.println("test2");
    }
}
