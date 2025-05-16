public class AnalisisBMI implements IHealthAnalyzer {
    private double berat;
    private double tinggi;
    private String jenisKelamin;
    private int umur;
    private double nilaiBMI;
    private String kategoriBMI;

    public AnalisisBMI(double berat, double tinggi, String jenisKelamin, int umur) {
        this.berat = berat;
        this.tinggi = tinggi;
        this.jenisKelamin = jenisKelamin;
        this.umur = umur;
        this.nilaiBMI = hitungBMI();
        this.kategoriBMI = tentukanKategoriBMI();
    }

    private double hitungBMI() {
        double tinggiMeter = tinggi / 100;
        return berat / (tinggiMeter * tinggiMeter);
    }

    private String tentukanKategoriBMI() {
        if (umur < 18) {
            return "BMI anak/bayi perlu dievaluasi dengan grafik pertumbuhan (tidak berlaku standar dewasa).";
        } else {
            if (nilaiBMI < 18.5) {
                return "Berat badan kurang";
            } else if (nilaiBMI >= 18.5 && nilaiBMI < 24.9) {
                return "Berat badan normal";
            } else if (nilaiBMI >= 25 && nilaiBMI < 29.9) {
                return "Berat badan berlebih";
            } else {
                return "Obesitas";
            }
        }
    }

    @Override
    public String analisis() {
        return String.format("BMI: %.2f - Kategori: %s", nilaiBMI, kategoriBMI);
    }

    @Override
    public String rekomendasi() {
        if (umur < 18) {
            return "Konsultasikan dengan dokter anak untuk evaluasi pertumbuhan yang tepat.";
        } else if (nilaiBMI < 18.5) {
            return "Tambahkan asupan kalori dan protein, konsumsi makanan bergizi seimbang.";
        } else if (nilaiBMI >= 18.5 && nilaiBMI < 24.9) {
            return "Pertahankan pola makan dan aktivitas fisik yang sehat.";
        } else if (nilaiBMI >= 25 && nilaiBMI < 29.9) {
            return "Kurangi asupan kalori dan tingkatkan aktivitas fisik secara bertahap.";
        } else {
            return "Konsultasikan dengan dokter untuk program penurunan berat badan yang aman dan efektif.";
        }
    }
}