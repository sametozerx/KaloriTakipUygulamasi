package com.proje.kaloritakipuygulamasi.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.proje.kaloritakipuygulamasi.database.entities.OgunKayit;
import com.proje.kaloritakipuygulamasi.util.TarihUtil;

@Dao
public interface OgunKayitDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertOgunKayit(OgunKayit ogunKayit);

    @Update
    void updateOgunKayit(OgunKayit ogunKayit);

    @Delete
    void deleteOgunKayit(OgunKayit ogunKayit);

    @Query("SELECT SUM(kalori) FROM ogun_kayit WHERE gun = :gun AND ay = :ay AND yil = :yil")
    int loadAllKaloriByDailies(int gun, int ay, int yil);

    @Query("INSERT INTO ogun_kayit VALUES (:kalori, :gun, :ay, :yil)")
    void insertOgunKayitKaloris(int kalori, int gun, int ay, int yil);

}
