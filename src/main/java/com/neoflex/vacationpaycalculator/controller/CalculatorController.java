package com.neoflex.vacationpaycalculator.controller;

import com.neoflex.vacationpaycalculator.services.CalculatorService;
import com.neoflex.vacationpaycalculator.services.ValidService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
import java.time.LocalDate;

@Controller
@RequestMapping("/calculate")
@RequiredArgsConstructor
public class CalculatorController {
    private final CalculatorService calculatorService;
    private final ValidService validService;

    @GetMapping
    public String calculate(@RequestParam(value = "mediumSalary", required = false) Double mediumSalary,
                            @RequestParam(value = "startVacation", required = false)
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startVacation,
                            @RequestParam(value = "endVacation", required = false)
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endVacation,
                            Model model) {

        model.addAttribute("mediumSalary", new DecimalFormat("#0").format(mediumSalary));
        model.addAttribute("startVacation", startVacation);
        model.addAttribute("endVacation", endVacation);

        boolean valid = validService.validParam(mediumSalary, startVacation, endVacation);

        if (valid) {
            model.addAttribute("vacationPay", calculatorService.getResult(mediumSalary,
                    startVacation, endVacation));
            return "answer";
        } else {
            return "error";
        }
    }
}