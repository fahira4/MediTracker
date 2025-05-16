public class AnalisisKebutuhanAir implements IHealthAnalyzer {
    private String jenisKelamin;
    private double berat;
    private double tinggi;
    private double kebutuhanAir;

    public AnalisisKebutuhanAir(String jenisKelamin, double berat, double tinggi) {
        this.jenisKelamin = jenisKelamin;
        this.berat = berat;
        this.tinggi = tinggi;
        this.kebutuhanAir = hitungKebutuhanAir();
    }

    private double hitungKebutuhanAir() {
        double jumlahDasar = (berat * 0.03) + (tinggi * 0.01);
        if (jenisKelamin.equalsIgnoreCase("male")) {
            return jumlahDasar + 0.5;
        } else {
            return jumlahDasar;
        }
    }

    @Override
    public String analisis() {
        return String.format("Kebutuhan Air: %.1f liter per hari", kebutuhanAir);
    }

    @Override
    public String rekomendasi() {
        if (kebutuhanAir > 3.5) {
            return "Minum secara teratur sepanjang hari.";
        } else if (kebutuhanAir > 2.5) {
            return "Bagi konsumsi air menjadi 7-8 gelas sehari. Minum segelas air saat bangun pagi.";
        } else {
            return "Bagi konsumsi air menjadi 6-7 gelas sehari. Minum air sebelum makan untuk membantu pencernaan.";
        }
    }
}