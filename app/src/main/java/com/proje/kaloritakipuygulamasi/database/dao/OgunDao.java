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

    @Query("SELECT * FROM ogun WHERE kategori_id in (SELECT kategori_id FROM ogun_kategori WHERE ogun_kategori_adi = :ad)")
    List <Ogun> loadOgunsByKategoriAd(String ad);

    @Query("SELECT * FROM ogun WHERE ogun_adi = :ad")
    List<Ogun> loadOgunsByOgunAd(String ad);
}
