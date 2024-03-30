package com.neoflex.vacationpaycalculator.services;

import java.time.LocalDate;

public interface CalculatorService {
    String getResult(Double averageSalary, LocalDate startDateOfVacation, LocalDate endDateOfVacation);
}