package taxcalculator;

import java.time.LocalDate;

/**
 *
 * @author Alvan
 */
public class Employee {
    private final PersonalInfo info;
    
    private final LocalDate joinDate;
    private final boolean isForeigner;
    
    private final Gender gender;; //Enum dari Gender.Java

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private FamilyInfo family;
    
    public Employee(PersonalInfo info, LocalDate joinDate, boolean isForeigner, Gender gender) {
        this.info = info;
        this.joinDate = joinDate;
        this.isForeigner = isForeigner;
        this.gender = gender;

        this.family = new FamilyInfo();
    }

    /**
     * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade
     * kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per
     * bulan, grade 3: 7.000.000 per bulan) Jika pegawai adalah warga negara
     * asing gaji bulanan diperbesar sebanyak 50%
     */
    public void setMonthlySalary(int grade) {
        final int GRADE1 = 3000000;
        final int GRADE2 = 5000000;
        final int GRADE3 = 7000000;
        final double FOREIGNER_MULTIPLIER = 1.5;
        
        int base = 0;
        
        switch (grade) {
            case 1: base = GRADE1; break;
            case 2: base = GRADE2; break;
            case 3: base = GRADE3; break;
            default: throw new IllegalArgumentException("Invalid grade");
        }
        this.monthlySalary = isForeigner
            ? (int) Math.round(base * FOREIGNER_MULTIPLIER)
            : base;
    }

    public void setAnnualDeductible(int deductible) {
        this.annualDeductible = deductible;
    }

    public void setAdditionalIncome(int income) {
        this.otherMonthlyIncome = income;
    }

    public void setSpouse(String spouseName, String spouseIdNumber) {
        family.setSpouse(new SpouseInfo(spouseName, spouseIdNumber));
    }

    public void addChild(String childName, String childIdNumber) {
        family.addChild(new ChildInfo(childName, childIdNumber));
    }
    
    private int calculateMonthsWorkedThisYear() {
        LocalDate today = LocalDate.now();
        if (today.getYear() == joinDate.getYear()) {
            return today.getMonthValue() - joinDate.getMonthValue();
        } else {
            return 12;
        }
    }

    public int getAnnualIncomeTax() {
        int monthsWorked = calculateMonthsWorkedThisYear();
        TaxProfile profile = new TaxProfile(
            monthlySalary,
            otherMonthlyIncome,
            monthsWorked,
            annualDeductible,
            family.hasSpouse(),
            family.getNumberOfChildren()
        );
        return TaxFunction.calculateTax(profile);
    }
}
