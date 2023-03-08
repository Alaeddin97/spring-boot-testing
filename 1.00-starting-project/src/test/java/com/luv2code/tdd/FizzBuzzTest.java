package com.luv2code.tdd;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FizzBuzzTest {

    @Order(1)
    @Test
    @DisplayName("Divisible by three")
    void testForDivisibleByThree(){
        String expected="Fizz";
        assertEquals(expected,FizzBuzz.compute(3),"this should be divisible by three");
    }

    @Order(2)
    @Test
    @DisplayName("Divisible by five")
    void testForDivisibleByFive(){
        String expected="Buzz";
        assertEquals(expected,FizzBuzz.compute(5),"this should be divisible by five");
    }

    @Order(3)
    @Test
    @DisplayName("Divisible by three and five")
    void testForDivisibleByThreeAndFive(){
        String expected="FizzBuzz";
        assertEquals(expected,FizzBuzz.compute(5*3),"this should be divisible by three and five");
    }

    @Order(4)
    @Test
    @DisplayName("Not divisible by three or five")
    void testForNotDivisibleByThreeOrFive(){
        assertEquals("7",FizzBuzz.compute(7),"this should not be divisible by three neither five");
    }


    @DisplayName("Testing with small data file")
    @ParameterizedTest(name = "value={0},expected={1}")
    @CsvFileSource(resources = "/small-test-data.csv")
    @Order(5)
    void testSmallCsvDataFile(int value,String expected){
        assertEquals(expected,FizzBuzz.compute(value));
    }

    @DisplayName("Testing with medium data file")
    @ParameterizedTest(name = "value={0},expected={1}")
    @CsvFileSource(resources = "/medium-test-data.csv")
    @Order(6)
    void testMediumCsvDataFile(int value,String expected){
        assertEquals(expected,FizzBuzz.compute(value));
    }

    @DisplayName("Testing with large data file")
    @ParameterizedTest(name = "value={0},expected={1}")
    @CsvFileSource(resources = "/large-test-data.csv")
    @Order(7)
    void testLargeCsvDataFile(int value,String expected){
        assertEquals(expected,FizzBuzz.compute(value));
    }


}
