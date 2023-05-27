package com.proje.kaloritakipuygulamasi.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ogun_kategori")
public class OgunKategori {
    @PrimaryKey(autoGenerate = true)
    int ogunKategoriId;

    @ColumnInfo(name = "ogun_kategori_adi")
    String kategoriAdi;

    public int getOgunKategoriId() {
        return ogunKategoriId;
    }

    public void setOgunKategoriId(int ogunKategoriId) {
        this.ogunKategoriId = ogunKategoriId;
    }

    public String getKategoriAdi() {
        return kategoriAdi;
    }

    public void setKategoriAdi(String kategoriAdi) {
        this.kategoriAdi = kategoriAdi;
    }
}
