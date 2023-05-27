package com.proje.kaloritakipuygulamasi.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.proje.kaloritakipuygulamasi.database.entities.OgunKategori;

import java.util.List;

@Dao
public interface OgunKategoriDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertOgunKategori(OgunKategori ogunKategori);

    @Update
    void updateOgunKategori(OgunKategori ogunKategori);

    @Delete
    void deleteOgunKategori(OgunKategori ogunKategori);

    @Query("SELECT * FROM ogun_kategori")
    List<OgunKategori> loadAllOgunKategoris();

    @Query("SELECT * FROM ogun_kategori WHERE ogunKategoriId = :id")
    OgunKategori loadOgunKategoriById(int id);

    @Query("SELECT ogunKategoriId FROM ogun_kategori WHERE ogun_kategori_adi = :ad")
    int loadOgunKategoriIdByOgunKategoriAd(String ad);
}
