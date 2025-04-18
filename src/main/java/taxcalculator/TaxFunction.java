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

    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {

        int tax = 0;

        if (numberOfMonthWorking > 12) {
            System.err.println("More than 12 month working per year");
        }

        if (numberOfChildren > MAX_CHILDREN) {
            numberOfChildren = 3;
        }

        if (isMarried) {
            tax = (int) Math.round(TAX_RATE * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - (BASE_EXEMPTION + SPOUSE_EXEMPTION + (numberOfChildren * 1500000))));
        } else {
            tax = (int) Math.round(TAX_RATE * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - BASE_EXEMPTION));
        }

        if (tax < 0) {
            return 0;
        } else {
            return tax;
        }

    }
}
