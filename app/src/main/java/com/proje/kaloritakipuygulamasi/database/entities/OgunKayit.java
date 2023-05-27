package com.proje.kaloritakipuygulamasi.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "ogun_kayit", foreignKeys = {
        @ForeignKey(
                entity = Kullanici.class,
                parentColumns = "kullaniciId",
                childColumns = "kullanici_id",
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE
        ),
        @ForeignKey(
                entity = Ogun.class,
                parentColumns = "ogunId",
                childColumns = "ogun_id",
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE
        )
})
public class OgunKayit {

    @PrimaryKey(autoGenerate = true)
    int ogunKayitId;

    @ColumnInfo(name = "kullanici_id")
    int kullaniciId;

    @ColumnInfo(name = "ogun_id")
    int ogunId;

    @ColumnInfo(name = "toplam_kalori")
    int toplamKalori;

    int gun;

    int ay;

    int yil;

    public int getOgunKayitId() {
        return ogunKayitId;
    }

    public void setOgunKayitId(int ogunKayitId) {
        this.ogunKayitId = ogunKayitId;
    }

    public int getKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(int kullaniciId) {
        this.kullaniciId = kullaniciId;
    }

    public int getOgunId() {
        return ogunId;
    }

    public void setOgunId(int ogunId) {
        this.ogunId = ogunId;
    }

    public int getToplamKalori() {
        return toplamKalori;
    }

    public void setToplamKalori(int toplamKalori) {
        this.toplamKalori = toplamKalori;
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
