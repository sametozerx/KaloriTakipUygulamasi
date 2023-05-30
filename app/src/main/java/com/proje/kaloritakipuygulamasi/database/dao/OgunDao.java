package com.proje.kaloritakipuygulamasi.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.proje.kaloritakipuygulamasi.database.entities.Ogun;

import java.util.List;

@Dao
public interface OgunDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertOgun(Ogun ogun);

    @Update
    void updateOgun(Ogun ogun);

    @Delete
    void deleteOgun(Ogun ogun);

    @Query("SELECT * FROM ogun")
    List<Ogun> loadAllOguns();

    @Query("SELECT * FROM ogun WHERE ogunId = :id")
    Ogun loadOgunByOgunId(int id);
    
    @Query("SELECT ogunId FROM ogun WHERE ogun_adi = :ad")
    int loadOgunIdByOgunAd(String ad);

    @Query("SELECT * FROM ogun WHERE birim_id in (SELECT birim_id FROM birim_kategori WHERE birim_kategori_adi = :ad)")
    List <Ogun> loadOgunsByBirimAd(String ad);

    @Query("SELECT * FROM ogun WHERE kategori_id in (SELECT kategori_id FROM ogun_kategori WHERE ogun_kategori_adi = :ad)")
    List <Ogun> loadOgunsByKategoriAd(String ad);

    @Query("SELECT ogun_kalori_miktari FROM ogun WHERE ogun_adi = :ad")
    int loadOgunKaloriMiktarByAd(String ad);
}
