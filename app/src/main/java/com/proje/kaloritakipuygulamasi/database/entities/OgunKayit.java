package com.proje.kaloritakipuygulamasi.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ogun_kayit")
public class OgunKayit {

    @PrimaryKey(autoGenerate = true)
    int ogunKayitId;

    int kalori;

    int gun;

    int ay;

    int yil;

    public int getOgunKayitId() {
        return ogunKayitId;
    }

    public void setOgunKayitId(int ogunKayitId) {
        this.ogunKayitId = ogunKayitId;
    }

    public int getKalori() {
        return kalori;
    }

    public void setKalori(int kalori) {
        this.kalori = kalori;
    }

    public int getGun() {
        return gun;
    }

    public void setGun(int gun) {
        this.gun = gun;
    }

    public int getAy() {
        return ay;
    }

    public void setAy(int ay) {
        this.ay = ay;
    }

    public int getYil() {
        return yil;
    }

    public void setYil(int yil) {
        this.yil = yil;
    }
}
