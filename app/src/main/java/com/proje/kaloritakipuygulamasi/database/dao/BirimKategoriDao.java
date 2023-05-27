package com.proje.kaloritakipuygulamasi.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.proje.kaloritakipuygulamasi.database.entities.BirimKategori;

import java.util.List;

@Dao
public interface BirimKategoriDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertBirimKategori(BirimKategori birimKategori);

    @Update
    void updateBirimKategori(BirimKategori birimKategori);

    @Delete
    void deleteBirimKategori(BirimKategori birimKategori);

    @Query("SELECT * FROM birim_kategori")
    List<BirimKategori> loadAllBirimKategoris();

    @Query("SELECT * FROM birim_kategori WHERE birimKategoriId = :id")
    BirimKategori loadBirimKategoriById(int id);

    @Query("SELECT birimKategoriId FROM birim_kategori WHERE birim_kategori_adi = :kategoriAd")
    int loadBirimKategoriIdByKategoriAd(String kategoriAd);

}
