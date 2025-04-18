package taxcalculator;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Alvan
 */
public class Employee {
    private final PersonalInfo info;

    private LocalDate joinDate;
    private int monthWorkingInYear;

    private boolean isForeigner;
    private Gender gender; //Enum dari Gender.Java

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private String spouseName;
    private String spouseIdNumber;

    private List<String> childNames;
    private List<String> childIdNumbers;

    public Employee(PersonalInfo info, LocalDate joinDate, boolean isForeigner, Gender gender) {
        this.info = info;
        this.joinDate = joinDate;
        this.isForeigner = isForeigner;
        this.gender = gender;

        childNames = new LinkedList<String>();
        childIdNumbers = new LinkedList<String>();
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
        this.spouseName = spouseName;
        this.spouseIdNumber = spouseIdNumber;
    }

    public void addChild(String childName, String childIdNumber) {
        childNames.add(childName);
        childIdNumbers.add(childIdNumber);
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
            spouseIdNumber.equals(""),
            childIdNumbers.size()
        );
        return TaxFunction.calculateTax(profile);
    }
}
