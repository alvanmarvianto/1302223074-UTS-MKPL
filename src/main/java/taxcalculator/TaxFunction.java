/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculator;

/**
 *
 * @author Alvan
 */
public class TaxFunction {

    /**
     * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus
     * dibayarkan setahun.
     *
     * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan
     * pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi
     * pemotongan) dikurangi penghasilan tidak kena pajak.
     *
     * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak
     * kena pajaknya adalah Rp 54.000.000. Jika pegawai sudah menikah maka
     * penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000. Jika
     * pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah
     * sebesar Rp 4.500.000 per anak sampai anak ketiga.
     *
     */
    
    // Constant untuk exemptions & limits
    private static final int BASE_EXEMPTION = 54_000_000;
    private static final int SPOUSE_EXEMPTION = 4_500_000;
    private static final int CHILD_EXEMPTION = 1_500_000;
    private static final int MAX_CHILDREN = 3;
    private static final double TAX_RATE = 0.05;
    
    /**
     * Menghitung pendapatan tahunan dari gaji bulanan dan pendapatan lainnya.
     */
    private static int computeAnnualIncome(int monthlySalary,
                                           int otherIncome,
                                           int monthsWorking) {
        return (monthlySalary + otherIncome) * monthsWorking;
    }
    
    /**
     * Menghitung total tunjangan bebas pajak dari status nikah dan jumlah anak.
     */
    private static int calculateNonTaxableIncome(boolean married, int children) {
        int exemption = BASE_EXEMPTION;
        if (married) {
            exemption += SPOUSE_EXEMPTION;
        }
        exemption += CHILD_EXEMPTION * children;
        return exemption;
    }

    /**
     * return pajak atau 0 jika ada kesalahan
     */
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
        // Guard Clauses
        if (numberOfMonthWorking > 12) {
            System.err.println("More than 12 months in a year!");
            return 0;
        }
        numberOfChildren = Math.min(numberOfChildren, MAX_CHILDREN);
        
        int grossIncome = computeAnnualIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking);
        int nonTaxable = calculateNonTaxableIncome(isMarried, numberOfChildren);
        int taxableBase   = grossIncome - deductible - nonTaxable;
        int rawTax        = (int) Math.round(TAX_RATE * taxableBase);
        return Math.max(rawTax, 0);
    }
}
