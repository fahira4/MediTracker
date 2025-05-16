
# MEDITRACKER: APLIKASI DIAGNOSA SEDERHANA

**MediTracker** adalah aplikasi Java berbasis console sederhana yang dirancang untuk membantu pengguna memantau lima aspek penting dalam kesehatan sehari-hari, yaitu:
- Durasi tidur
- Kebutuhan air harian
- Kecukupan gizi (kalori)
- Aktivitas fisik
- Indeks Massa Tubuh (BMI)

Dengan memberikan data sederhana seperti jam tidur, berat badan, aktivitas harian, dan konsumsi kalori, aplikasi ini akan memberikan analisis berbasis logika sederhana untuk membantu pengguna memahami status kesehatannya.

---

## TUJUAN APLIKASI

Memberikan *feedback* cepat terkait gaya hidup sehari-hari agar pengguna bisa mengetahui apakah kebiasaan mereka sudah mendukung hidup sehat atau masih perlu ditingkatkan.

---

## STRUKTUR KELAS

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
├── SecondaryController.java    # Opsional: class tambahan untuk pengontrolan alur (tidak wajib tergantung arsitektur)
```

## DESKRIPSI KELAS

### `IHealthAnalyzer`
Interface dengan satu method:
```java
String analisis();
```

```java
String rekomendasi();
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

### `SecondaryController`
Kelas opsional yang berfungsi sebagai pengendali tambahan dalam aplikasi jika digunakan pendekatan multi-scene atau modular control.

---

## CARA MENJALANKAN PROGRAM

1. **Buka folder proyek di VSCode atau IDE favoritmu.**
2. **Pastikan semua file `.java` berada dalam satu folder (tidak perlu package khusus).**
3. **Compile semua file:**
   ```bash
   javac <options> <source files> *.java
   ```
   contoh:

   ```bash
   javac --module-path "C:\Program Files\Java\javafx-21\lib" --add-modules javafx.controls,javafx.fxml -d target/classes src/*.java
   ```
4. **Jalankan program dari file utama:**
   ```bash
   java <options> <source files> AplikasiUtama
   ```
   contoh:

   ```bash
   java --module-path "C:\Program Files\Java\javafx-21\lib" --add-modules javafx.controls,javafx.fxml -cp target/classes AplikasiUtama
   ```

---

## CATATAN

- Program ini berbasis input dari pengguna. Pastikan kamu memasukkan data yang masuk akal.
- Jika ingin menambahkan fitur baru, cukup buat class baru yang mengimplementasikan `IHealthAnalyzer`.

---

## PEMBAGIAN TUGAS

1. Nurul Fakhira Amanah M.Adil (fahira4)
- Membuat kelas AnalisisAir, AnalisisAktivitas, AnalisisBMI, dan AplikasiUtama.
- Membuat antarmuka aplikasi menggunakan JavaFX dengan struktur proyek Maven.
2. Aisyah Salsabila Sabri (babssz)
-  Membuat kelas Diagnosa dan AnalisisTidur.
- Membuat antarmuka aplikasi menggunakan JavaFX dengan struktur proyek Maven.
3. Muhammad Daffa Usman (daffa2222)
-  Membuat kelas AnalisisGizi dan IHealthAnalyzer.
- Membuat antarmuka aplikasi menggunakan JavaFX dengan struktur proyek Maven.

## 👩‍💻 Author

- GitHub: [fahira4](https://github.com/fahira4)
- GitHub: [babssz](https://github.com/babssz)
- GitHub: [daffa2222](https://github.com/daffa2222)
