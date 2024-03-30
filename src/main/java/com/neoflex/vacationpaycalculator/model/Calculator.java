package com.neoflex.vacationpaycalculator.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class Calculator {
    private Double mediumSalary;
    private LocalDate startVacation;
    private LocalDate endVacation;
}