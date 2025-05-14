package com.example;
public class Gizi implements IHealthAnalyzer {
    private int umur;
    private double beratBadan;
    private double tinggiBadan;
    private String jenisKelamin;
    private String aktivitas;
    private int kebutuhanKalori;

    public Gizi(int umur, double beratBadan, double tinggiBadan, String jenisKelamin, String aktivitas) {
        this.umur = umur;
        this.beratBadan = beratBadan;
        this.tinggiBadan = tinggiBadan;
        this.jenisKelamin = jenisKelamin;
        this.aktivitas = aktivitas;
        this.kebutuhanKalori = hitungKebutuhanKalori();
    }

    private int hitungKebutuhanKalori() {
        double BMR;
        
        // Menghitung BMR berdasarkan jenis kelamin
        if (jenisKelamin.equalsIgnoreCase("Pria")) {
            BMR = 88.362 + (13.397 * beratBadan) + (4.799 * tinggiBadan) - (5.677 * umur);
        } else {
            BMR = 447.593 + (9.247 * beratBadan) + (3.098 * tinggiBadan) - (4.330 * umur);
        }
        
        // Menghitung kebutuhan kalori berdasarkan aktivitas
        double faktorAktivitas = 1.2; // Default jika aktivitas tidak sesuai
        switch (aktivitas.toLowerCase()) {
            case "sangat tidak aktif":
                faktorAktivitas = 1.2;
                break;
            case "ringan":
                faktorAktivitas = 1.375;
                break;
            case "sedang":
                faktorAktivitas = 1.55;
                break;
            case "aktif":
                faktorAktivitas = 1.725;
                break;
            case "sangat aktif":
                faktorAktivitas = 1.9;
                break;
        }
        
        // Menghitung kebutuhan kalori harian
        return (int) (BMR * faktorAktivitas);
    }

    @Override
    public String analyze() {
        return String.format("Kebutuhan Kalori: %d kalori per hari (Tingkat aktivitas: %s)", kebutuhanKalori, aktivitas);
    }

    @Override
    public String recommend() {
        StringBuilder rekomendasi = new StringBuilder();
        
        if (kebutuhanKalori < 1500) {
            rekomendasi.append("Konsumsi makanan padat gizi untuk memenuhi kebutuhan nutrisi dengan kalori terbatas. ");
        } else if (kebutuhanKalori > 2500) {
            rekomendasi.append("Pastikan asupan protein yang cukup untuk mendukung aktivitas fisik Anda. ");
        }
        
        rekomendasi.append("Bagi asupan kalori ke dalam 3 makanan utama dan 2 makanan ringan. ");
        rekomendasi.append("Utamakan sumber karbohidrat kompleks, protein tanpa lemak, dan lemak sehat.");
        
        return rekomendasi.toString();
    }
}