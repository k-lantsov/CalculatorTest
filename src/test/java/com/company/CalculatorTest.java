package com.company;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calculator;

    @BeforeAll
    static void setup() {
        System.out.println("Executing @BeforeAll method.");
    }

    @AfterAll
    static void cleanup() {
        System.out.println("Executing @AfterAll method.");
    }

    @BeforeEach
    void beforeEachTestMethod() {
        calculator = new Calculator();
        System.out.println("Executing @BeforeEach method.");
    }

    @AfterEach
    void afterEachTestMethod() {
        System.out.println("Executing @AfterEach method.");
    }

    @DisplayName("Test 4/2 = 2")
    @Test
    void testIntegerDivision_WhenFourIsDividedByTwo_ShouldReturnTwo() {
        //Arrange
        int dividend = 4;
        int divisor = 2;
        int expectedResult = 2;

        //Act
        int actualResult = calculator.integerDivision(dividend, divisor);

        //Assert
        assertEquals(expectedResult, actualResult, dividend + "/" + divisor + " did not produce " + expectedResult);
    }

    //@Disabled("TODO: Still need to work on it")
    @DisplayName("Division by zero")
    @Test
    void testIntegerDivision_WhenDividendIsDividedByZero_ShouldThrowArithmeticException() {
        System.out.println("Running Division by zero");
        //Arrange
        int dividend = 4;
        int divisor = 0;
        String expectedExceptionMessage = "/ by zero";
        //Act & Assert
        ArithmeticException actualException = assertThrows(ArithmeticException.class,
                () -> calculator.integerDivision(dividend, divisor),
                "Division by zero should have thrown an Arithmetic exception.");
        assertEquals(expectedExceptionMessage, actualException.getMessage(), "Unexpected exception message");

    }

    @ParameterizedTest
    @ValueSource(strings = {"John", "Kate", "Alice"})
    void valueSourceDemonstration(String firstname) {
        System.out.println(firstname);
        assertNotNull(firstname);
    }

    @DisplayName("Test integer subtraction [minuend, subtrahend, expectedResult]")
    @ParameterizedTest
//    @MethodSource()
    @CsvFileSource(resources = "/integerSubtraction.csv")
    void integerSubtraction(int minuend, int subtrahend, int expectedResult) {
        System.out.println("Running Test: " + minuend + " - " + subtrahend + " = " + expectedResult);
        int actualResult = calculator.integerSubtraction(minuend, subtrahend);
        assertEquals(expectedResult, actualResult, () -> minuend + " - " + subtrahend + " did not produce " + expectedResult);
    }

//    private static Stream<Arguments> integerSubtraction() {
//        return Stream.of(
//                Arguments.of(10, 2, 8),
//                Arguments.of(33, 1, 32),
//                Arguments.of(54, 10, 44)
//        );
//    }
}