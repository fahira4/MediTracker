public class AnalisisAktivitas implements IHealthAnalyzer {
    private int durasiMenit;

    public AnalisisAktivitas(int durasiMenit) {
        this.durasiMenit = durasiMenit;
    }

    public int getDurasiMenit() {
        return durasiMenit;
    }

    public void setDurasiMenit(int durasiMenit) {
        this.durasiMenit = durasiMenit;
    }

    @Override
    public String analisis() {
        return String.format("Aktivitas Fisik: %d menit per hari", durasiMenit);
    }

    @Override
    public String rekomendasi() {
        if (durasiMenit < 15) {
            return "Anda sangat kurang beraktivitas. Mulailah dengan 15 menit berjalan kaki setiap hari.";
        } else if (durasiMenit < 30) {
            return "Aktivitas Anda masih kurang. Tingkatkan hingga minimal 30 menit per hari.";
        } else if (durasiMenit < 60) {
            return "Aktivitas Anda sudah cukup baik. Pertahankan dan variasikan jenis aktivitas fisik.";
        } else {
            return "Aktivitas Anda sudah sangat baik. Pastikan istirahat yang cukup untuk pemulihan.";
        }
    }
}