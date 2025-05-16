public class AnalisisGizi implements IHealthAnalyzer {
    private int umur;
    private double berat;
    private double tinggi;
    private String jenisKelamin;
    private String tingkatAktivitas;
    private int kebutuhanKalori;

    public AnalisisGizi(int umur, double berat, double tinggi, String jenisKelamin, String tingkatAktivitas) {
        this.umur = umur;
        this.berat = berat;
        this.tinggi = tinggi;
        this.jenisKelamin = jenisKelamin;
        this.tingkatAktivitas = tingkatAktivitas;
        this.kebutuhanKalori = hitungKebutuhanKalori();
    }

    private int hitungKebutuhanKalori() {
        double BMR;
        
        if (jenisKelamin.equalsIgnoreCase("Pria")) {
            BMR = 88.362 + (13.397 * berat) + (4.799 * tinggi) - (5.677 * umur);
        } else {
            BMR = 447.593 + (9.247 * berat) + (3.098 * tinggi) - (4.330 * umur);
        }
        
        double faktorAktivitas = 1.2;
        switch (tingkatAktivitas.toLowerCase()) {
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
        
        return (int) (BMR * faktorAktivitas);
    }

    @Override
    public String analisis() {
        return String.format("Kebutuhan Kalori: %d kalori per hari (Tingkat aktivitas: %s)", kebutuhanKalori, tingkatAktivitas);
    }

    @Override
    public String rekomendasi() {
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