package com.proje.kaloritakipuygulamasi.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.proje.kaloritakipuygulamasi.database.entities.Ogun;
import com.proje.kaloritakipuygulamasi.database.entities.OgunKayit;

import java.util.List;

@Dao
public interface OgunKayitDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertOgunKayit(OgunKayit ogunKayit);

    @Update
    void updateOgunKayit(OgunKayit ogunKayit);

    @Delete
    void deleteOgunKayit(OgunKayit ogunKayit);

    @Query("SELECT * FROM ogun_kayit")
    List<OgunKayit> loadOgunKayits();

    @Query("SELECT * FROM ogun_kayit WHERE ogunKayitId = :id")
    OgunKayit loadOgunKayitById(int id);

    @Query("SELECT * FROM ogun_kayit WHERE gun = :gun AND ay = :ay AND yil = :yil")
    OgunKayit loadOgunKayitByTarih(int gun, int ay, int yil);

    @Query("SELECT * FROM ogun_kayit WHERE ogunKayitId = :id AND gun = :gun AND ay = :ay AND yil = :yil AND ogun_id IN (SELECT ogun_id FROM ogun WHERE kategori_id IN(SELECT kategori_id FROM ogun_kategori WHERE ogun_kategori_adi = :ad))")
    OgunKayit loadOgunKayitByTarihAndKategori(int id, int gun, int ay, int yil, String ad);

    @Query("DELETE FROM ogun_kayit WHERE ogunKayitId = :id AND gun = :gun AND ay = :ay AND yil = :yil AND ogun_id IN (SELECT ogun_id FROM ogun WHERE kategori_id IN(SELECT kategori_id FROM ogun_kategori WHERE ogun_kategori_adi = :ad))")
    OgunKayit deleteOgunKayit(int id, int gun, int ay, int yil, String ad);
}
