package com.example.fifin.sistem_pakar_tbc;

import java.lang.ref.SoftReference;

public class DataPertanyaan {
    private String id;
    private String pertanyaan;
    private String jawabanYa;
    private String jawabanTidak;

    public DataPertanyaan(String id, String pertanyaan, String jawabanYa, String jawabanTidak) {
        this.id = id;
        this.pertanyaan = pertanyaan;
        this.jawabanYa = jawabanYa;
        this.jawabanTidak = jawabanTidak;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public String getJawabanYa() {
        return jawabanYa;
    }

    public void setJawabanYa(String jawabanYa) {
        this.jawabanYa = jawabanYa;
    }

    public String getJawabanTidak() {
        return jawabanTidak;
    }

    public void setJawabanTidak(String jawabanTidak) {
        this.jawabanTidak = jawabanTidak;
    }
}
