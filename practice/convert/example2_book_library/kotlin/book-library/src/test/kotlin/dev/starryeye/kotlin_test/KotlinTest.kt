package dev.starryeye.kotlin_test

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class KotlinTest {

    companion object {
        @BeforeAll
        @JvmStatic
        fun beforeAll() {
            println("Before All")
        }

        @AfterAll
        @JvmStatic
        fun afterAll() {
            println("After All")
        }
    }

    @BeforeEach
    fun beforeEach() {
        println("Before Each")
    }

    @AfterEach
    fun afterEach() {
        println("After Each")
    }

    @Test
    fun test1() {
        println("Test1")
    }

    @Test
    fun test2() {
        println("Test2")
    }
}