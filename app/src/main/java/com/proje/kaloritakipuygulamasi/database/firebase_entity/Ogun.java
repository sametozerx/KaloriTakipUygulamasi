package com.proje.kaloritakipuygulamasi.database.firebase_entity;

public class Ogun {
    private String ogunAd;
    private int ogunKalori;
    private String ogunBirim;

    public Ogun(){

    }

    public Ogun(String ogunAd, int ogunKalori, String ogunBirim, String ogunKategori){
        this.ogunAd = ogunAd;
        this.ogunKalori = ogunKalori;
        this.ogunBirim = ogunBirim;

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


}
