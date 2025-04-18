package taxcalculator;

import java.time.LocalDate;

/**
 *
 * @author Alvan
 */
public class Employee {
    private final PersonalInfo personal;
    
    private final LocalDate joinDate;
    private final boolean isForeigner;
    
    private final Gender gender; //Enum dari Gender.Java

    private SalaryInfo salary;
    
    private FamilyInfo family;
    
    public Employee(PersonalInfo personal, LocalDate joinDate, boolean isForeigner, Gender gender) {
        this.personal = personal;
        this.joinDate = joinDate;
        this.isForeigner = isForeigner;
        this.gender = gender;

        this.salary = new SalaryInfo();
        this.family = new FamilyInfo();
    }

    /**
     * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade
     * kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per
     * bulan, grade 3: 7.000.000 per bulan) Jika pegawai adalah warga negara
     * asing gaji bulanan diperbesar sebanyak 50%
     */
    public void setMonthlySalary(int grade) {
        salary.setSalary(grade, isForeigner);
    }

    public void setAnnualDeductible(int deductible) {
        salary.setAnnualDeductible(deductible);
    }

    public void setAdditionalIncome(int income) {
        salary.setOtherMonthlyIncome(income);
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
            salary.getMonthlySalary(),
            salary.getOtherMonthlyIncome(),
            monthsWorked,
            salary.getAnnualDeductible(),
            family.hasSpouse(),
            family.getNumberOfChildren()
        );
        return TaxFunction.calculateTax(profile);
    }
}
