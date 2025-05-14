package com.example;
import java.time.LocalTime;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tidur implements IHealthAnalyzer {
    private String tanggal;
    private LocalTime jamTidur;
    private LocalTime jamBangun;
    private double durasiTidur;
    private String status;

    public Tidur(String tanggal, String jamTidurStr, String jamBangunStr) {
        this.tanggal = tanggal;
        this.jamTidur = LocalTime.parse(jamTidurStr);
        this.jamBangun = LocalTime.parse(jamBangunStr);
        hitungDurasi();
        this.status = getStatus();
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public LocalTime getJamTidur() {
        return jamTidur;
    }

    public void setJamTidur(LocalTime jamTidur) {
        this.jamTidur = jamTidur;
    }

    public LocalTime getJamBangun() {
        return jamBangun;
    }

    public void setJamBangun(LocalTime jamBangun) {
        this.jamBangun = jamBangun;
    }

    public double getDurasiTidur() {
        return durasiTidur;
    }

    public String getStatusTidur() {
        return status;
    }

    private void hitungDurasi() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date;

        try {
            date = LocalDate.parse(tanggal, formatter);
        } catch (Exception e) {
            date = LocalDate.now();
        }

        LocalDateTime tidurDateTime = LocalDateTime.of(date, jamTidur);
        LocalDateTime bangunDateTime = LocalDateTime.of(date, jamBangun);

        if (jamBangun.isBefore(jamTidur)) {
            bangunDateTime = bangunDateTime.plusDays(1);
        }

        Duration durasi = Duration.between(tidurDateTime, bangunDateTime);
        this.durasiTidur = durasi.toMinutes() / 60.0;
    }

    private String getStatus() {
        if (durasiTidur < 6) return "kurang";
        else if (durasiTidur <= 9) return "cukup";
        else return "berlebihan";
    }

    @Override
    public String analyze() {
        return String.format("Durasi Tidur: %.2f jam - Status: %s", durasiTidur, status);
    }

    @Override
    public String recommend() {
        if (status.equals("kurang")) {
            return "Anda kurang tidur. Usahakan tidur 7-9 jam sehari untuk kesehatan optimal.";
        } else if (status.equals("cukup")) {
            return "Durasi tidur Anda sudah ideal. Pertahankan jadwal tidur yang teratur.";
        } else {
            return "Anda terlalu banyak tidur. Kurangi durasi tidur dan tingkatkan aktivitas fisik.";
        }
    }
}