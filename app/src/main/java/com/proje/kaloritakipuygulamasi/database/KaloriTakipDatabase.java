package com.proje.kaloritakipuygulamasi.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.proje.kaloritakipuygulamasi.database.dao.BirimKategoriDao;
import com.proje.kaloritakipuygulamasi.database.dao.KullaniciDao;
import com.proje.kaloritakipuygulamasi.database.dao.OgunDao;
import com.proje.kaloritakipuygulamasi.database.dao.OgunKategoriDao;
import com.proje.kaloritakipuygulamasi.database.dao.OgunKayitDao;
import com.proje.kaloritakipuygulamasi.database.entities.BirimKategori;
import com.proje.kaloritakipuygulamasi.database.entities.Kullanici;
import com.proje.kaloritakipuygulamasi.database.entities.Ogun;
import com.proje.kaloritakipuygulamasi.database.entities.OgunKategori;
import com.proje.kaloritakipuygulamasi.database.entities.OgunKayit;

@Database(entities = {Kullanici.class, BirimKategori.class, OgunKategori.class, Ogun.class, OgunKayit.class}, version = 1)
public abstract class KaloriTakipDatabase extends RoomDatabase {
    private static KaloriTakipDatabase kaloriTakipDatabase;

    public abstract KullaniciDao kullaniciDao();
    public abstract OgunKategoriDao ogunKategoriDao();
    public abstract BirimKategoriDao birimKategoriDao();
    public abstract OgunDao ogunDao();
    public abstract OgunKayitDao ogunKayitDao();

    private static final String databaseName = "cal.db";

    public static KaloriTakipDatabase getKaloriTakipDatabase(Context context){
        if (kaloriTakipDatabase == null) {
            kaloriTakipDatabase = Room.databaseBuilder(context, KaloriTakipDatabase.class, databaseName).allowMainThreadQueries().build();
        }
        return kaloriTakipDatabase;
    }
}
