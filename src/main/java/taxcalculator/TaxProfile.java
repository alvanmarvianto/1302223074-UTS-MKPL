/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculator;

/**
 *
 * @author Alvan
 */
public class TaxProfile {
    private final int monthlySalary;
    private final int otherMonthlyIncome;
    private final int monthsWorking;
    private final int deductible;
    private final boolean married;
    private final int children;

    public TaxProfile(int monthlySalary,
                      int otherMonthlyIncome,
                      int monthsWorking,
                      int deductible,
                      boolean married,
                      int children) {
        this.monthlySalary       = monthlySalary;
        this.otherMonthlyIncome  = otherMonthlyIncome;
        this.monthsWorking       = monthsWorking;
        this.deductible          = deductible;
        this.married             = married;
        this.children            = Math.min(children, TaxFunction.MAX_CHILDREN);
    }

    public int getMonthlySalary() {
        return monthlySalary;
    }

    public int getOtherMonthlyIncome() {
        return otherMonthlyIncome;
    }

    public int getMonthsWorking() {
        return monthsWorking;
    }

    public int getDeductible() {
        return deductible;
    }

    public boolean isMarried() {
        return married;
    }

    public int getChildren() {
        return children;
    }
}
