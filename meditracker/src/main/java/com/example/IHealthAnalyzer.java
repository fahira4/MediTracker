package com.example;
public interface IHealthAnalyzer {
    /**
     * Menganalisis data kesehatan dan mengembalikan hasil analisis
     * @return hasil analisis dalam bentuk String
     */
    String analyze();
    
    /**
     * Memberikan rekomendasi berdasarkan hasil analisis
     * @return rekomendasi dalam bentuk String
     */
    String recommend();
}