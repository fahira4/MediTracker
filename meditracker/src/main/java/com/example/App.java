package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import java.time.format.DateTimeFormatter;
import java.net.URL;

public class App extends Application {

    private TextArea outputArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Aplikasi Diagnosa Kesehatan");

        // Create a blue-themed style
        String blueStyle = "-fx-background-color: #f0f8ff; -fx-border-color: #1e90ff; -fx-border-radius: 5; -fx-padding: 5;";
        String buttonStyle = "-fx-background-color: #1e90ff; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8 15;";
        String comboBoxStyle = "-fx-background-color: #f0f8ff; -fx-border-color: #1e90ff; -fx-border-radius: 5;";
        String textAreaStyle = "-fx-control-inner-background: #f0f8ff; -fx-border-color: #1e90ff; -fx-border-radius: 5;";

        // Input Fields with blue styling
        TextField nameField = new TextField();
        nameField.setPromptText("Nama");
        nameField.setStyle(blueStyle);

        TextField ageField = new TextField();
        ageField.setPromptText("Usia");
        ageField.setStyle(blueStyle);

        TextField weightField = new TextField();
        weightField.setPromptText("Berat Badan (kg)");
        weightField.setStyle(blueStyle);

        TextField heightField = new TextField();
        heightField.setPromptText("Tinggi Badan (cm)");
        heightField.setStyle(blueStyle);

        ComboBox<String> genderBox = new ComboBox<>();
        genderBox.getItems().addAll("male", "female");
        genderBox.setPromptText("Gender");
        genderBox.setStyle(comboBoxStyle);

        TextField activityField = new TextField();
        activityField.setPromptText("Durasi Aktivitas (menit)");
        activityField.setStyle(blueStyle);

        TextField sleepTimeField = new TextField();
        sleepTimeField.setPromptText("Jam Tidur (HH:mm)");
        sleepTimeField.setStyle(blueStyle);

        TextField wakeTimeField = new TextField();
        wakeTimeField.setPromptText("Jam Bangun (HH:mm)");
        wakeTimeField.setStyle(blueStyle);

        ComboBox<String> aktivitasBox = new ComboBox<>();
        aktivitasBox.getItems().addAll("Sangat Tidak Aktif", "Ringan", "Sedang", "Aktif", "Sangat Aktif");
        aktivitasBox.setPromptText("Tingkat Aktivitas");
        aktivitasBox.setStyle(comboBoxStyle);

        Button diagnoseButton = new Button("Diagnosa");
        diagnoseButton.setStyle(buttonStyle);

        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setWrapText(true);
        outputArea.setPrefHeight(300);
        outputArea.setStyle(textAreaStyle);

        diagnoseButton.setOnAction(e -> {
            try {
                String nama = nameField.getText().trim();
                if (nama.isEmpty() || !nama.matches("[a-zA-Z\\s]+")) {
                    outputArea.setText("Nama tidak boleh kosong dan hanya boleh berisi huruf dan spasi.");
                    return;
                }

                int usia = Integer.parseInt(ageField.getText());
                if (usia <= 0 || usia > 120) {
                    outputArea.setText("Usia harus angka valid antara 1 - 120.");
                    return;
                }

                double berat = Double.parseDouble(weightField.getText());
                if (berat <= 0 || berat > 300) {
                    outputArea.setText("Berat badan harus angka valid (> 0 dan realistis).");
                    return;
                }

                double tinggi = Double.parseDouble(heightField.getText());
                if (tinggi <= 0 || tinggi > 250) {
                    outputArea.setText("Tinggi badan harus angka valid (> 0 dan realistis).");
                    return;
                }

                String gender = genderBox.getValue();
                if (gender == null || (!gender.equals("male") && !gender.equals("female"))) {
                    outputArea.setText("Silakan pilih gender (male/female).");
                    return;
                }

                int durasiAktivitas = Integer.parseInt(activityField.getText());
                if (durasiAktivitas < 0 || durasiAktivitas > 1440) {
                    outputArea.setText("Durasi aktivitas harus antara 0 - 1440 menit.");
                    return;
                }

                String jamTidur = sleepTimeField.getText().trim();
                String jamBangun = wakeTimeField.getText().trim();
                if (!jamTidur.matches("\\d{2}:\\d{2}") || !jamBangun.matches("\\d{2}:\\d{2}")) {
                    outputArea.setText("Format jam tidur/bangun harus HH:mm (misal 22:30).");
                    return;
                }

                String aktivitas = aktivitasBox.getValue();
                if (aktivitas == null || aktivitas.isEmpty()) {
                    outputArea.setText("Silakan pilih tingkat aktivitas.");
                    return;
                }

                // Jika semua valid, lanjutkan proses diagnosa
                String tanggal = java.time.LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                String jenisKelamin = gender.equals("male") ? "Pria" : "Wanita";

                Diagnosa diagnosa = new Diagnosa(nama, usia);
                diagnosa.tambahAnalyzer(new BMI(berat, tinggi, gender, usia));
                diagnosa.tambahAnalyzer(new WaterAnalyzer(gender, berat, tinggi));
                diagnosa.tambahAnalyzer(new AktivitasAnalyzer(durasiAktivitas));
                diagnosa.tambahAnalyzer(new Tidur(tanggal, jamTidur, jamBangun));
                diagnosa.tambahAnalyzer(new Gizi(usia, berat, tinggi, jenisKelamin, aktivitas));

                StringBuilder hasil = new StringBuilder();
                hasil.append("===== HASIL DIAGNOSA UNTUK: ").append(nama.toUpperCase()).append(" =====\n")
                        .append("Usia: ").append(usia).append(" tahun\n")
                        .append("-----------------------------------\n");

                for (IHealthAnalyzer analyzer : diagnosa.getAnalyzers()) {
                    hasil.append(analyzer.analyze()).append("\n");
                    hasil.append("Rekomendasi: ").append(analyzer.recommend()).append("\n");
                    hasil.append("-----------------------------------\n");
                }

                hasil.append("Terima kasih telah menggunakan aplikasi diagnosa kesehatan.");
                outputArea.setText(hasil.toString());

            } catch (NumberFormatException ex) {
                outputArea.setText("Usia, berat, tinggi, dan durasi aktivitas harus berupa angka.");
            } catch (Exception ex) {
                outputArea.setText("Terjadi kesalahan: " + ex.getMessage());
            }
        });

        // Setup layout and background image
        VBox layout = new VBox(10,
                nameField, ageField, weightField, heightField,
                genderBox, activityField,
                sleepTimeField, wakeTimeField,
                aktivitasBox, diagnoseButton, outputArea
        );

        layout.setPadding(new Insets(20));

        // Load background image
        URL resource = getClass().getResource("/images/background.png");
        if (resource == null) {
            outputArea.setText("Gambar background tidak ditemukan.");
            return;
        }

        BackgroundImage bgImage = new BackgroundImage(
            new Image(resource.toExternalForm(), 480, 650, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
        );
        layout.setBackground(new Background(bgImage));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 480, 650);
        primaryStage.setScene(scene);
        primaryStage.show();

        System.out.println(getClass().getResource("/images/background.png"));
    }
}
