public interface IHealthAnalyzer {
//Menganalisis data kesehatan dan mengembalikan hasil analisis
    String analisis();
    
//Memberikan rekomendasi berdasarkan hasil analisis
    String rekomendasi();
}
