package com.neoflex.vacationpaycalculator.services;

import com.neoflex.vacationpaycalculator.model.Calculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CalculatorServiceImpl implements CalculatorService {
    public static final String FORMAT_OF_VACATION_PAY = "#0.00";
    public static final double AVERAGE_AMOUNT_DAY_IN_MONTH = 29.3;
    private final Calculator calculator;

    @Override
    public String getResult(Double mediumSalary, LocalDate startVacation, LocalDate endVacation) {
        String result = null;

        calculator.setMediumSalary(mediumSalary);
        calculator.setStartVacation(startVacation);
        calculator.setEndVacation(endVacation);
        result = new DecimalFormat(FORMAT_OF_VACATION_PAY).format(
                calculator.getMediumSalary() / AVERAGE_AMOUNT_DAY_IN_MONTH * getAmountVacationDays());

        return result;
    }

    private int getAmountVacationDays() {
        int amountVacationDays = 0;
        for (LocalDate i = calculator.getStartVacation(); !i.isAfter(calculator.getEndVacation());
             i = i.plusDays(1)) {
            if (!isHoliday(i)) {
                amountVacationDays++;
            }
        }
        return amountVacationDays;
    }

    private boolean isHoliday(LocalDate localDate) {
        return PublicHolidays.publicHolidays.contains(toMonthDay(localDate));
    }

    private static MonthDay toMonthDay(LocalDate localDate) {
        return MonthDay.of(localDate.getMonth(), localDate.getDayOfMonth());
    }

    private static class PublicHolidays {
        public final static Set<MonthDay> publicHolidays = new HashSet<>();

        static {
            publicHolidays.add(MonthDay.of(Month.JANUARY, 1));
            publicHolidays.add(MonthDay.of(Month.JANUARY, 2));
            publicHolidays.add(MonthDay.of(Month.JANUARY, 3));
            publicHolidays.add(MonthDay.of(Month.JANUARY, 4));
            publicHolidays.add(MonthDay.of(Month.JANUARY, 5));
            publicHolidays.add(MonthDay.of(Month.JANUARY, 6));
            publicHolidays.add(MonthDay.of(Month.JANUARY, 7));
            publicHolidays.add(MonthDay.of(Month.JANUARY, 8));
            publicHolidays.add(MonthDay.of(Month.FEBRUARY, 23));
            publicHolidays.add(MonthDay.of(Month.MARCH, 8));
            publicHolidays.add(MonthDay.of(Month.MAY, 1));
            publicHolidays.add(MonthDay.of(Month.MAY, 9));
            publicHolidays.add(MonthDay.of(Month.JUNE, 12));
            publicHolidays.add(MonthDay.of(Month.NOVEMBER, 4));
        }
    }
}