package com.neoflex.vacationpaycalculator.services;

import com.neoflex.vacationpaycalculator.model.Calculator;
import junit.framework.TestCase;

import java.time.LocalDate;

public class CalculatorTest extends TestCase {
    public void testWithValidParametersWithoutHolidays() {
        CalculatorService calculatorService = new CalculatorServiceImpl(new Calculator());
        String actual = calculatorService.getResult(120000.00,
                LocalDate.of(2023, 5, 15),
                LocalDate.of(2023, 5, 19));
        String expected = "20477,82";
        assertEquals(expected, actual);
    }

    public void testWithValidParametersWithHolidays() {
        CalculatorService calculatorService = new CalculatorServiceImpl(new Calculator());
        String actual = calculatorService.getResult(120000.00,
                LocalDate.of(2023, 5, 1),
                LocalDate.of(2023, 5, 14));
        String expected = "49146,76";
        assertEquals(expected, actual);
    }
}