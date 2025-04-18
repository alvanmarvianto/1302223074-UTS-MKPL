/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculator;

/**
 *
 * @author Alvan
 */
public class SalaryInfo {

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    public void setSalary(int grade, boolean isForeigner) {
        final int GRADE1 = 3000000;
        final int GRADE2 = 5000000;
        final int GRADE3 = 7000000;
        final double FOREIGNER_MULTIPLIER = 1.5;

        int base = 0;

        switch (grade) {
            case 1:
                base = GRADE1;
                break;
            case 2:
                base = GRADE2;
                break;
            case 3:
                base = GRADE3;
                break;
            default:
                throw new IllegalArgumentException("Invalid grade");
        }
        this.monthlySalary = isForeigner
                ? (int) Math.round(base * FOREIGNER_MULTIPLIER)
                : base;
    }

    public void setOtherMonthlyIncome(int income) {
        this.otherMonthlyIncome = income;
    }

    public void setAnnualDeductible(int deductible) {
        this.annualDeductible = deductible;
    }

    public int getMonthlySalary() {
        return monthlySalary;
    }

    public int getOtherMonthlyIncome() {
        return otherMonthlyIncome;
    }

    public int getAnnualDeductible() {
        return annualDeductible;
    }
}
