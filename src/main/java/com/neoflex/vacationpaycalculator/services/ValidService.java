package com.neoflex.vacationpaycalculator.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ValidService {
    public boolean validParam(Double mediumSalary, LocalDate startVacation, LocalDate endVacation) {
        boolean valid = (mediumSalary > 0 && startVacation.isBefore(endVacation));
        return valid;
    }
}
