package com.example;
import java.util.List;
import java.util.ArrayList;

public class Diagnosa {
    private List<IHealthAnalyzer> analyzers;
    private String nama;
    private int usia;

    public Diagnosa(String nama, int usia) {
        this.nama = nama;
        this.usia = usia;
        this.analyzers = new ArrayList<>();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getUsia() {
        return usia;
    }

    public void setUsia(int usia) {
        this.usia = usia;
    }

    public List<IHealthAnalyzer> getAnalyzers() {
        return analyzers;
    }

    public void tambahAnalyzer(IHealthAnalyzer analyzer) {
        analyzers.add(analyzer);
    }

    public void hasilDiagnosa() {
        System.out.println("\n===== HASIL DIAGNOSA KESEHATAN =====");
        System.out.println("Nama: " + nama);
        System.out.println("Usia: " + usia + " tahun");
        System.out.println("-----------------------------------");

        for (IHealthAnalyzer analyzer : analyzers) {
            System.out.println(analyzer.analyze());
            System.out.println("Rekomendasi: " + analyzer.recommend());
            System.out.println("-----------------------------------");
        }

        System.out.println("Terima kasih telah menggunakan aplikasi diagnosa kesehatan.");
    }
}