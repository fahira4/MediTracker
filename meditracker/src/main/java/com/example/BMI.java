package com.example;
public class BMI implements IHealthAnalyzer {
    private double beratBadan;
    private double tinggiBadan;
    private String gender;
    private int usia;
    private double bmiValue;
    private String kategori;

    public BMI(double beratBadan, double tinggiBadan, String gender, int usia) {
        this.beratBadan = beratBadan;
        this.tinggiBadan = tinggiBadan;
        this.gender = gender;
        this.usia = usia;
        this.bmiValue = hitungBMI();
        this.kategori = kategoriBMI();
    }

    public double getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(double beratBadan) {
        this.beratBadan = beratBadan;
        this.bmiValue = hitungBMI();
        this.kategori = kategoriBMI();
    }

    public double getTinggiBadan() {
        return tinggiBadan;
    }

    public void setTinggiBadan(double tinggiBadan) {
        this.tinggiBadan = tinggiBadan;
        this.bmiValue = hitungBMI();
        this.kategori = kategoriBMI();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getUsia() {
        return usia;
    }

    public void setUsia(int usia) {
        this.usia = usia;
        this.kategori = kategoriBMI();
    }

    public double getBmiValue() {
        return bmiValue;
    }

    public String getKategori() {
        return kategori;
    }

    private double hitungBMI() {
        double tinggiMeter = tinggiBadan / 100;
        return beratBadan / (tinggiMeter * tinggiMeter);
    }

    private String kategoriBMI() {
        if (usia < 18) {
            return "BMI anak/bayi perlu dievaluasi dengan grafik pertumbuhan (tidak berlaku standar dewasa).";
        } else {
            if (bmiValue < 18.5) {
                return "Berat badan kurang";
            } else if (bmiValue >= 18.5 && bmiValue < 24.9) {
                return "Berat badan normal";
            } else if (bmiValue >= 25 && bmiValue < 29.9) {
                return "Berat badan berlebih";
            } else {
                return "Obesitas";
            }
        }
    }

    @Override
    public String analyze() {
        return String.format("BMI: %.2f - Kategori: %s", bmiValue, kategori);
    }

    @Override
    public String recommend() {
        if (usia < 18) {
            return "Konsultasikan dengan dokter anak untuk evaluasi pertumbuhan yang tepat.";
        } else if (bmiValue < 18.5) {
            return "Tambahkan asupan kalori dan protein, konsumsi makanan bergizi seimbang.";
        } else if (bmiValue >= 18.5 && bmiValue < 24.9) {
            return "Pertahankan pola makan dan aktivitas fisik yang sehat.";
        } else if (bmiValue >= 25 && bmiValue < 29.9) {
            return "Kurangi asupan kalori dan tingkatkan aktivitas fisik secara bertahap.";
        } else {
            return "Konsultasikan dengan dokter untuk program penurunan berat badan yang aman dan efektif.";
        }
    }
}