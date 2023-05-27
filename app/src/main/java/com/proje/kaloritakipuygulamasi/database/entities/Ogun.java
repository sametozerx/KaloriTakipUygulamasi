package com.proje.kaloritakipuygulamasi.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "ogun", foreignKeys = {
        @ForeignKey(
                entity = BirimKategori.class,
                parentColumns = "birimKategoriId",
                childColumns = "birim_id",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        ),
        @ForeignKey(
                entity = OgunKategori.class,
                parentColumns = "ogunKategoriId",
                childColumns = "kategori_id",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        )
})
public class Ogun {
    @PrimaryKey(autoGenerate = true)
    int ogunId;

    @ColumnInfo(name = "ogun_adi")
    String ogunAdi;

    @ColumnInfo(name = "birim_id")
    int birimId;

    @ColumnInfo(name = "ogun_kalori_miktari")
    int ogunKaloriMiktari;

    @ColumnInfo(name = "kategori_id")
    int kategoriId;

    public int getOgunId() {
        return ogunId;
    }

    public void setOgunId(int ogunId) {
        this.ogunId = ogunId;
    }

    public String getOgunAdi() {
        return ogunAdi;
    }

    public void setOgunAdi(String ogunAdi) {
        this.ogunAdi = ogunAdi;
    }

    public int getBirimId() {
        return birimId;
    }

    public void setBirimId(int birimId) {
        this.birimId = birimId;
    }

    public int getOgunKaloriMiktari() {
        return ogunKaloriMiktari;
    }

    public void setOgunKaloriMiktari(int ogunKaloriMiktari) {
        this.ogunKaloriMiktari = ogunKaloriMiktari;
    }

    public int getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(int kategoriId) {
        this.kategoriId = kategoriId;
    }
}
