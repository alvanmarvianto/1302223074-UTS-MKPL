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

    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;

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

    public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, LocalDate joinDate, boolean isForeigner, Gender gender) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.address = address;
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
            default: break;
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
        this.spouseIdNumber = idNumber;
    }

    public void addChild(String childName, String childIdNumber) {
        childNames.add(childName);
        childIdNumbers.add(childIdNumber);
    }

    public int getAnnualIncomeTax() {

        //Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
        LocalDate date = LocalDate.now();

        if (date.getYear() == joinDate.getYear()) {
            monthWorkingInYear = date.getMonthValue() - joinDate.getMonthValue();
        } else {
            monthWorkingInYear = 12;
        }

        return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
    }
}
