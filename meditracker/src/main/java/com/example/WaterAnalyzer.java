package com.example;
public class WaterAnalyzer implements IHealthAnalyzer {
    private String gender;
    private double weight;
    private double height;
    private double recommendedWater;

    public WaterAnalyzer(String gender, double weight, double height) {
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.recommendedWater = rekomendasiLiterAir();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getRecommendedWater() {
        return recommendedWater;
    }

    private double rekomendasiLiterAir() {
        double baseAmount = (weight * 0.03) + (height * 0.01);
        if (gender.equalsIgnoreCase("male")) {
            return baseAmount + 0.5;
        } else {
            return baseAmount;
        }
    }

    @Override
    public String analyze() {
        return String.format("Kebutuhan Air: %.1f liter per hari", recommendedWater);
    }

    @Override
    public String recommend() {
        if (recommendedWater > 3.5) {
            return "Minum secara teratur sepanjang hari.";
        } else if (recommendedWater > 2.5) {
            return "Bagi konsumsi air menjadi 7-8 gelas sehari. Minum segelas air saat bangun pagi.";
        } else {
            return "Bagi konsumsi air menjadi 6-7 gelas sehari. Minum air sebelum makan untuk membantu pencernaan.";
        }
    }
}