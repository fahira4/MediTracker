import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AplikasiUtama extends Application {

    // Komponen UI
    private TextField fieldNama, fieldUmur, fieldBerat, fieldTinggi, fieldAktivitas, fieldJamTidur, fieldJamBangun;
    private ComboBox<String> comboJenisKelamin, comboTingkatAktivitas;
    private VBox containerHasil;
    private Stage stageUtama;
    private Scene sceneHasil;
    private static Scene scene;
    
    // Konstanta Gaya
    private static final String GAYA_JUDUL = "-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill:rgb(16, 70, 124);";
    private static final String GAYA_SUB_JUDUL = "-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #3498db;";
    private static final String GAYA_REKOMENDASI = "-fx-font-size: 14px; -fx-text-fill:rgb(137, 238, 238); -fx-font-style: italic;";
    private static final String GAYA_INFORMASI = "-fx-font-size: 14px; -fx-text-fill: #2c3e50;";
    private static final String GAYA_KARTU = "-fx-background-color: #FFFDF6; -fx-background-radius: 10; -fx-padding: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 0);";
    private static final String GAYA_LATAR = "-fx-background-color: rgb(194, 225, 244);";
    private static final String GAYA_LATAR_MINT = "-fx-background-color: rgb(195, 223, 247);";
    private static final String GAYA_TOMBOL = "-fx-background-color:rgb(67, 157, 231); -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 18px; -fx-background-radius: 8; -fx-padding: 15 30;";


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stageUtama) {
        this.stageUtama = stageUtama;
        stageUtama.setTitle("MEDITRACKER - Aplikasi Diagnosa Kesehatan");
        
        inisialisasiSceneInput();
        inisialisasiSceneHasil();
        
        stageUtama.setScene(buatSceneInput());
        stageUtama.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AplikasiUtama.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    private Scene buatSceneInput() {
        Label labelJudul = new Label("MEDITRACKER");
        labelJudul.setStyle(GAYA_JUDUL);
        labelJudul.setPadding(new Insets(0, 0, 10, 0));
        
        fieldNama = buatFieldTeks("Masukkan nama lengkap");
        fieldUmur = buatFieldTeks("Masukkan usia dalam tahun");
        fieldBerat = buatFieldTeks("Masukkan berat badan (kg)");
        fieldTinggi = buatFieldTeks("Masukkan tinggi badan (cm)");
        fieldAktivitas = buatFieldTeks("Durasi aktivitas harian (menit)");
        fieldJamTidur = buatFieldTeks("Contoh: 22:30");
        fieldJamBangun = buatFieldTeks("Contoh: 06:30");
        
        comboJenisKelamin = buatComboBox("Pilih jenis kelamin", "Pria", "Wanita");
        comboTingkatAktivitas = buatComboBox("Pilih tingkat aktivitas", 
            "Sangat Tidak Aktif", "Ringan", "Sedang", "Aktif", "Sangat Aktif");

        GridPane gridForm = new GridPane();
        gridForm.setHgap(20);
        gridForm.setVgap(15);
        gridForm.setPadding(new Insets(20));
        gridForm.setMinWidth(550);
        
        tambahBarisForm(gridForm, 0, "Nama", fieldNama);
        tambahBarisForm(gridForm, 1, "Usia", fieldUmur);
        tambahBarisForm(gridForm, 2, "Berat Badan (kg)", fieldBerat);
        tambahBarisForm(gridForm, 3, "Tinggi Badan (cm)", fieldTinggi);
        tambahBarisForm(gridForm, 4, "Jenis Kelamin", comboJenisKelamin);
        tambahBarisForm(gridForm, 5, "Durasi Aktivitas (menit)", fieldAktivitas);
        tambahBarisForm(gridForm, 6, "Jam Tidur (HH:mm)", fieldJamTidur);
        tambahBarisForm(gridForm, 7, "Jam Bangun (HH:mm)", fieldJamBangun);
        tambahBarisForm(gridForm, 8, "Tingkat Aktivitas", comboTingkatAktivitas);

        Button tombolDiagnosa = new Button("Lakukan Diagnosa");
        tombolDiagnosa.setStyle(GAYA_TOMBOL);
        tombolDiagnosa.setOnAction(e -> prosesDiagnosa());

        VBox layoutUtama = new VBox(15, labelJudul, gridForm, tombolDiagnosa);
        layoutUtama.setAlignment(Pos.TOP_CENTER);
        layoutUtama.setPadding(new Insets(20));
        layoutUtama.setStyle(GAYA_LATAR_MINT); 

        return new Scene(layoutUtama, 600, 700);
    }


    private void inisialisasiSceneHasil() {
        containerHasil = new VBox(20);
        containerHasil.setPadding(new Insets(25));
        containerHasil.setStyle(GAYA_LATAR);
        
        ScrollPane scrollPane = new ScrollPane(containerHasil);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        Button tombolKembali = new Button("Kembali");
        tombolKembali.setStyle(GAYA_TOMBOL);
        tombolKembali.setOnAction(e -> stageUtama.setScene(buatSceneInput()));
        
        Button tombolKeluar = new Button("Keluar");
        tombolKeluar.setStyle(GAYA_TOMBOL.replace("#3498db", "#e74c3c"));
        tombolKeluar.setOnAction(e -> stageUtama.close());
        
        HBox boxTombol = new HBox(20, tombolKembali, tombolKeluar);
        boxTombol.setAlignment(Pos.CENTER);
        boxTombol.setPadding(new Insets(20, 0, 0, 0));
        
        VBox layout = new VBox(scrollPane, boxTombol);
        layout.setStyle(GAYA_LATAR);
        
        sceneHasil = new Scene(layout, 700, 750);
    }

    private void prosesDiagnosa() {
        try {
            if (!validasiInput()) return;
            
            String nama = fieldNama.getText().trim();
            int umur = Integer.parseInt(fieldUmur.getText());
            
            // Validasi input lainnya
            if (fieldBerat.getText().isEmpty() || fieldTinggi.getText().isEmpty() || 
                fieldAktivitas.getText().isEmpty() || fieldJamTidur.getText().isEmpty() || 
                fieldJamBangun.getText().isEmpty() || comboJenisKelamin.getValue() == null ||
                comboTingkatAktivitas.getValue() == null) {
                
                tampilkanPeringatan("Error", "Semua field harus diisi");
                return;
            }
            
            double berat = Double.parseDouble(fieldBerat.getText());
            double tinggi = Double.parseDouble(fieldTinggi.getText());
            
            // Validasi nilai berat dan tinggi
            if (berat <= 0 || berat > 300) {
                tampilkanPeringatan("Error", "Berat badan harus antara 1-300 kg");
                return;
            }
            
            if (tinggi <= 0 || tinggi > 250) {
                tampilkanPeringatan("Error", "Tinggi badan harus antara 1-250 cm");
                return;
            }
            
            String jenisKelamin = comboJenisKelamin.getValue().equals("Pria") ? "male" : "female";
            int durasiAktivitas = Integer.parseInt(fieldAktivitas.getText());
            String jamTidur = fieldJamTidur.getText().trim();
            String jamBangun = fieldJamBangun.getText().trim();
            String tingkatAktivitas = comboTingkatAktivitas.getValue();
            String tanggal = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            
            // Validasi format jam
            if (!validasiFormatJam(jamTidur) || !validasiFormatJam(jamBangun)) {
                tampilkanPeringatan("Error", "Format jam tidak valid. Gunakan format: HH:mm (contoh: 22:30)");
                return;
            }
            
            Diagnosa diagnosa = new Diagnosa(nama, umur);
            diagnosa.tambahAnalisis(new AnalisisBMI(berat, tinggi, jenisKelamin, umur));
            diagnosa.tambahAnalisis(new AnalisisKebutuhanAir(jenisKelamin, berat, tinggi));
            diagnosa.tambahAnalisis(new AnalisisAktivitas(durasiAktivitas));
            diagnosa.tambahAnalisis(new AnalisisTidur(tanggal, jamTidur, jamBangun));
            diagnosa.tambahAnalisis(new AnalisisGizi(umur, berat, tinggi, comboJenisKelamin.getValue(), tingkatAktivitas));
            
            tampilkanHasil(diagnosa, nama, umur, berat, tinggi);
            
        } catch (Exception e) {
            tampilkanPeringatan("Error", "Terjadi kesalahan: " + e.getMessage());
        }
    }
    
    // Validasi format jam (HH:mm)
    private boolean validasiFormatJam(String jam) {
        if (jam == null || jam.isEmpty()) return false;
        
        // Validasi format HH:mm
        return jam.matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
    }

    private void tampilkanHasil(Diagnosa diagnosa, String nama, int umur, double berat, double tinggi) {
        containerHasil.getChildren().clear();
        
        Label header = new Label("HASIL DIAGNOSA UNTUK: " + nama.toUpperCase());
        header.setStyle(GAYA_JUDUL);
        containerHasil.getChildren().add(header);
        
        VBox kartuInfo = buatKartu("INFORMASI DASAR");
        HBox boxInfo = new HBox(30);
        
        double nilaiBMI = berat / Math.pow(tinggi/100, 2);
        Label labelUmur = buatLabelInfo("Usia: " + umur + " tahun");
        Label labelJenisKelamin = buatLabelInfo("Jenis Kelamin: " + comboJenisKelamin.getValue());
        Label labelBMI = buatLabelInfo(String.format("BMI: %.1f", nilaiBMI));
        
        boxInfo.getChildren().addAll(labelUmur, labelJenisKelamin, labelBMI);
        kartuInfo.getChildren().add(boxInfo);
        containerHasil.getChildren().add(kartuInfo);
        
        List<IHealthAnalyzer> daftarAnalisis = diagnosa.getDaftarAnalisis();
        for (IHealthAnalyzer analisis : daftarAnalisis) {
            // Menggunakan nama kelas yang sebenarnya dari object analisis
            String judulKartu = formatNamaKelas(analisis.getClass().getSimpleName());
            VBox kartuAnalisis = buatKartu(judulKartu);
            
            Text teksHasil = new Text(analisis.analisis());
            teksHasil.setStyle(GAYA_INFORMASI);
            teksHasil.setWrappingWidth(600);
            
            Text judulRekomendasi = new Text("Rekomendasi:");
            judulRekomendasi.setStyle(GAYA_SUB_JUDUL.replace("18px", "16px"));
            
            Text teksRekomendasi = new Text(analisis.rekomendasi());
            teksRekomendasi.setStyle(GAYA_REKOMENDASI);
            teksRekomendasi.setWrappingWidth(600);
            
            VBox boxRekomendasi = new VBox(5, judulRekomendasi, teksRekomendasi);
            boxRekomendasi.setPadding(new Insets(10, 0, 0, 0));
            
            kartuAnalisis.getChildren().addAll(teksHasil, boxRekomendasi);
            containerHasil.getChildren().add(kartuAnalisis);
        }
        
        Label footer = new Label("Terima kasih telah menggunakan MEDITRACKER");
        footer.setStyle("-fx-font-style: italic; -fx-text-fill: #7f8c8d;");
        footer.setPadding(new Insets(20, 0, 0, 0));
        containerHasil.getChildren().add(footer);
        
        stageUtama.setScene(sceneHasil);
    }
    
    // Method untuk memformat nama kelas menjadi format yang lebih mudah dibaca
    private String formatNamaKelas(String namaKelas) {
        // Menangani kasus-kasus nama kelas yang diketahui
        switch (namaKelas) {
            case "AnalisisBMI":
                return "BMI";
            case "AnalisisKebutuhanAir":
                return "KEBUTUHAN AIR";
            case "AnalisisAktivitas":
                return "AKTIVITAS";
            case "AnalisisTidur":
                return "TIDUR";
            case "AnalisisGizi":
                return "GIZI";
            default:
                // Jika nama kelas tidak dikenali, format secara umum
                return namaKelas.replace("Analisis", "").toUpperCase();
        }
    }

    // Method pembantu
    private TextField buatFieldTeks(String petunjuk) {
        TextField field = new TextField();
        field.setPromptText(petunjuk);
        field.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color:rgb(173, 225, 231);");
        field.setPrefHeight(35);
        field.setPrefWidth(300); // Mengatur lebar field menjadi lebih panjang
        return field;
    }
    
    private ComboBox<String> buatComboBox(String petunjuk, String... items) {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(items);
        comboBox.setPromptText(petunjuk);
        comboBox.setStyle("-fx-background-radius: 5; -fx-border-radius: 5;");
        comboBox.setPrefHeight(35);
        comboBox.setPrefWidth(300); // Mengatur lebar combobox menjadi lebih panjang
        return comboBox;
    }
    
    private void tambahBarisForm(GridPane grid, int baris, String teksLabel, Control field) {
        Label label = new Label(teksLabel);
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        grid.add(label, 0, baris);
        
        // Mengatur preferensi kolom agar tetap memiliki lebar yang konsisten
        if (grid.getColumnConstraints().isEmpty()) {
            ColumnConstraints kolom1 = new ColumnConstraints();
            kolom1.setMinWidth(150);
            kolom1.setPrefWidth(150);
            
            ColumnConstraints kolom2 = new ColumnConstraints();
            kolom2.setMinWidth(300);
            kolom2.setPrefWidth(300);
            
            grid.getColumnConstraints().addAll(kolom1, kolom2);
        }
        
        grid.add(field, 1, baris);
    }
    
    private VBox buatKartu(String judul) {
        Label labelJudul = new Label(judul);
        labelJudul.setStyle(GAYA_SUB_JUDUL);
        
        VBox kartu = new VBox(10, labelJudul);
        kartu.setStyle(GAYA_KARTU);
        kartu.setPadding(new Insets(15));
        return kartu;
    }
    
    private Label buatLabelInfo(String teks) {
        Label label = new Label(teks);
        label.setStyle(GAYA_INFORMASI);
        return label;
    }
    
    private boolean validasiInput() {
        if (fieldNama.getText().isEmpty()) {
            tampilkanPeringatan("Error", "Nama tidak boleh kosong");
            return false;
        }
        
        // Validasi usia
        try {
            if (!fieldUmur.getText().isEmpty()) {
                int umur = Integer.parseInt(fieldUmur.getText());
                if (umur > 100) {
                    tampilkanPeringatan("Error", "Usia tidak boleh lebih dari 100 tahun");
                    return false;
                } else if (umur <= 0) {
                    tampilkanPeringatan("Error", "Usia harus lebih dari 0 tahun");
                    return false;
                }
            } else {
                tampilkanPeringatan("Error", "Usia tidak boleh kosong");
                return false;
            }
        } catch (NumberFormatException e) {
            tampilkanPeringatan("Error", "Usia harus berupa angka");
            return false;
        }
        
        return true;
    }
    
    private void tampilkanPeringatan(String judul, String pesan) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(judul);
        alert.setHeaderText(null);
        alert.setContentText(pesan);
        alert.showAndWait();
    }

    private void inisialisasiSceneInput() {
        // Diimplementasikan sesuai kebutuhan
    }
}