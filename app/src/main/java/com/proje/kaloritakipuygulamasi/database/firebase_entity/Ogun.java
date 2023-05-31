package com.proje.kaloritakipuygulamasi.database.firebase_entity;

public class Ogun {
    private String ogunAd;
    private int ogunKalori;
    private String ogunBirim;
    private String ogunKategori;

    public Ogun(){

    }

    public Ogun(String ogunAd, int ogunKalori, String ogunBirim, String ogunKategori){
        this.ogunAd = ogunAd;
        this.ogunKalori = ogunKalori;
        this.ogunBirim = ogunBirim;
        this.ogunKategori = ogunKategori;
    }

    public String getOgunAd() {
        return ogunAd;
    }

    public void setOgunAd(String ogunAd) {
        this.ogunAd = ogunAd;
    }

    public int getOgunKalori() {
        return ogunKalori;
    }

    public void setOgunKalori(int ogunKalori) {
        this.ogunKalori = ogunKalori;
    }

    public String getOgunBirim() {
        return ogunBirim;
    }

    public void setOgunBirim(String ogunBirim) {
        this.ogunBirim = ogunBirim;
    }

    public String getOgunKategori() {
        return ogunKategori;
    }

    public void setOgunKategori(String ogunKategori) {
        this.ogunKategori = ogunKategori;
    }
}
