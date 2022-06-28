package com.company;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DemoRepeatedTest {
    Calculator calculator;

    @BeforeEach
    void beforeEachTestMethod() {
        calculator = new Calculator();
        System.out.println("Executing @BeforeEach method.");
    }

    @AfterEach
    void afterEachTestMethod() {
        System.out.println("Executing @AfterEach method.");
    }

    @DisplayName("Division by zero")
    @RepeatedTest(value = 3, name = "Repetition {currentRepetition} of {totalRepetitions}")
    void testIntegerDivision_WhenDividendIsDividedByZero_ShouldThrowArithmeticException(
            RepetitionInfo repetitionInfo,
            TestInfo testInfo
    ) {
        System.out.println("Running " + testInfo.getTestMethod().get().getName());
        System.out.println("Repetition #" + repetitionInfo.getCurrentRepetition() + " of " + repetitionInfo.getTotalRepetitions());

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
}
