package com.proje.kaloritakipuygulamasi.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "birim_kategori")
public class BirimKategori {
    @PrimaryKey(autoGenerate = true)
    int birimKategoriId;

    @ColumnInfo(name = "birim_kategori_adi")
    String birimKategoriAdi;

    public int getBirimKategoriId() {
        return birimKategoriId;
    }

    public void setBirimKategoriId(int birimKategoriId) {
        this.birimKategoriId = birimKategoriId;
    }

    public String getBirimKategoriAdi() {
        return birimKategoriAdi;
    }

    public void setBirimKategoriAdi(String birimKategoriAdi) {
        this.birimKategoriAdi = birimKategoriAdi;
    }
}
