package com.proje.kaloritakipuygulamasi.database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "kullanici")
public class Kullanici {

    @PrimaryKey(autoGenerate = true)
    int kullaniciId;

    @ColumnInfo(name = "kullanici_adi")
    String kullaniciAdi;

    @ColumnInfo(name = "kullanici_yas")
    int kullaniciYas;

    String cinsiyet;

    @ColumnInfo(name = "kullanici_boy")
    int kullaniciBoy;

    @ColumnInfo(name = "kullanici_kilo")
    int kullaniciKilo;

    @ColumnInfo(name = "kullanici_gereken_kalori")
    int kullaniciGerekenKalori;

    String deviceId;

    public int getKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(int kullaniciId) {
        this.kullaniciId = kullaniciId;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public int getKullaniciYas() {
        return kullaniciYas;
    }

    public void setKullaniciYas(int kullaniciYas) {
        this.kullaniciYas = kullaniciYas;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public int getKullaniciBoy() {
        return kullaniciBoy;
    }

    public void setKullaniciBoy(int kullaniciBoy) {
        this.kullaniciBoy = kullaniciBoy;
    }

    public int getKullaniciKilo() {
        return kullaniciKilo;
    }

    public void setKullaniciKilo(int kullaniciKilo) {
        this.kullaniciKilo = kullaniciKilo;
    }

    public int getKullaniciGerekenKalori() {
        return kullaniciGerekenKalori;
    }

    public void setKullaniciGerekenKalori(int kullaniciGerekenKalori) {
        this.kullaniciGerekenKalori = kullaniciGerekenKalori;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
