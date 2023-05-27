package com.proje.kaloritakipuygulamasi.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.proje.kaloritakipuygulamasi.database.entities.Kullanici;

import java.util.List;

@Dao
public interface KullaniciDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertKullanici(Kullanici kullanici);

    @Update
    void updateKullanici(Kullanici kullanici);

    @Delete
    void deleteKUllanici(Kullanici kullanici);

    @Query("SELECT * FROM kullanici")
    List<Kullanici> loadAllKullanicis();

    @Query("SELECT * FROM kullanici WHERE kullaniciId = :id")
    Kullanici loadKullaniciById(int id);

    @Query("SELECT kullanici_gereken_kalori FROM kullanici WHERE kullaniciId = :id")
    int LoadKullaniciKaloriById(int id);

    @Query("UPDATE kullanici set kullanici_adi = :ad WHERE kullaniciId = :id")
    void updateKullaniciAdById(String ad, int id);

    @Query("UPDATE kullanici set kullanici_boy = :boy WHERE kullaniciId = :id")
    void updateKullaniciBoyById(int boy, int id);

    @Query("UPDATE kullanici set kullanici_kilo = :kilo WHERE kullaniciId = :id")
    void updateKullaniciKiloById(int kilo, int id);

    @Query("UPDATE kullanici set kullanici_gereken_kalori = :boy WHERE kullaniciId = :id")
    void updateKullaniciKaloriById(int boy, int id);

}
