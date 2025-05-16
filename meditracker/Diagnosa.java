import java.util.ArrayList;
import java.util.List;

public class Diagnosa {
    private String nama;
    private int umur;
    private List<IHealthAnalyzer> daftarAnalisis;

    public Diagnosa(String nama, int umur) {
        this.nama = nama;
        this.umur = umur;
        this.daftarAnalisis = new ArrayList<>();
    }

    public void tambahAnalisis(IHealthAnalyzer analisis) {
        daftarAnalisis.add(analisis);
    }

    public List<IHealthAnalyzer> getDaftarAnalisis() {
        return daftarAnalisis;
    }

    public String getNama() { return nama; }
    public int getUmur() { return umur; }
}