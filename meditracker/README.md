
# MediTrack

**MediTrack** adalah aplikasi Java berbasis console sederhana yang dirancang untuk membantu pengguna memantau lima aspek penting dalam kesehatan sehari-hari, yaitu:
- Durasi tidur
- Kebutuhan air harian
- Kecukupan gizi (kalori)
- Aktivitas fisik
- Indeks Massa Tubuh (BMI)

Dengan memberikan data sederhana seperti jam tidur, berat badan, aktivitas harian, dan konsumsi kalori, aplikasi ini akan memberikan analisis berbasis logika sederhana untuk membantu pengguna memahami status kesehatannya secara menyeluruh.

---

## 🎯 Tujuan Aplikasi

Memberikan *feedback* cepat terkait gaya hidup sehari-hari agar pengguna bisa mengetahui apakah kebiasaan mereka sudah mendukung hidup sehat atau masih perlu ditingkatkan.

---

## 📂 Struktur Kelas

```
├── IHealthAnalyzer.java         # Interface yang wajib diimplementasikan oleh semua class analisis
├── AnalisisTidur.java          # Analisis durasi tidur
├── AnalisisKebutuhanAir.java   # Analisis kebutuhan air harian
├── AnalisisGizi.java           # Analisis kecukupan kalori
├── AnalisisAktivitas.java      # Analisis aktivitas fisik
├── AnalisisBMI.java            # Analisis indeks massa tubuh
├── Diagnosa.java               # Menggabungkan seluruh analisis ke dalam 1 laporan
├── AplikasiUtama.java          # Entry point (main method)
├── PrimaryController.java      # Opsional: class tambahan untuk pengontrolan alur (tidak wajib tergantung arsitektur)
```

---

## 🧠 Deskripsi Kelas

### `IHealthAnalyzer`
Interface dengan satu method:
```java
String analisis();
```
Semua class analisis akan mengimplementasikan interface ini.

---

### `AnalisisTidur`
Menganalisis apakah durasi tidur pengguna sudah sesuai dengan standar (6–8 jam/hari).

---

### `AnalisisKebutuhanAir`
Mengecek apakah jumlah air yang dikonsumsi per hari cukup berdasarkan berat badan dan aktivitas.

---

### `AnalisisGizi`
Menilai apakah asupan kalori pengguna sesuai kebutuhan berdasarkan berat badan dan aktivitas fisik.

---

### `AnalisisAktivitas`
Menganalisis durasi aktivitas fisik harian (apakah cukup atau kurang bergerak).

---

### `AnalisisBMI`
Menghitung dan menginterpretasikan nilai BMI berdasarkan berat dan tinggi badan.

---

### `Diagnosa`
Menggabungkan semua analisis dari kelas di atas untuk menghasilkan laporan komprehensif mengenai status kesehatan pengguna.

---

### `AplikasiUtama`
Merupakan class utama tempat program dieksekusi. Mengatur input dari user, membuat objek analisis, dan menampilkan hasil.

---

### `PrimaryController`
Sebagai pengendali jalannya aplikasi, jika arsitektur MVC digunakan. Bisa menangani logika alur dan transisi data antarkomponen.

---

## ▶️ Cara Menjalankan Program

1. **Buka folder proyek di VSCode atau IDE favoritmu.**
2. **Pastikan semua file `.java` berada dalam satu folder (tidak perlu package khusus).**
3. **Compile semua file:**
   ```bash
   javac *.java
   ```
4. **Jalankan program dari file utama:**
   ```bash
   java AplikasiUtama
   ```

---

## 💡 Catatan

- Program ini berbasis input dari pengguna. Pastikan kamu memasukkan data yang masuk akal.
- Jika ingin menambahkan fitur baru, cukup buat class baru yang mengimplementasikan `IHealthAnalyzer`.
