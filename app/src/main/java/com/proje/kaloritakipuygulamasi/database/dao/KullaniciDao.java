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

    @Query("SELECT * FROM kullanici WHERE kullaniciId = 1")
    Kullanici loadFirstKullanici();

    @Query("SELECT kullanici_gereken_kalori FROM kullanici WHERE kullaniciId = 1")
    int LoadFirstKullaniciKalori();

    @Query("UPDATE kullanici set kullanici_adi = :ad WHERE kullaniciId = 1")
    void updateFirstKullaniciAd(String ad);

    @Query("UPDATE kullanici set kullanici_boy = :boy WHERE kullaniciId = 1")
    void updateFirstKullaniciBoy(int boy);

    @Query("UPDATE kullanici set kullanici_kilo = :kilo WHERE kullaniciId = 1")
    void updateFirstKullaniciKilo(int kilo);

    @Query("UPDATE kullanici set kullanici_gereken_kalori = :kalori WHERE kullaniciId = 1")
    void updateFirstKullaniciKalori(int kalori);

    @Query("UPDATE kullanici set kullanici_yas = :yas WHERE kullaniciId = 1")
    void updateFirstKullaniciYas(int yas);

    @Query("UPDATE kullanici set cinsiyet = :cinsiyet WHERE kullaniciId = 1")
    void updateFirstKullaniciCinsiyet(String cinsiyet);
}
