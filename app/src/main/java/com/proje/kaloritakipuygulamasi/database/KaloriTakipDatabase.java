package com.proje.kaloritakipuygulamasi.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

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

@Database(entities = {Kullanici.class, BirimKategori.class, OgunKategori.class, Ogun.class, OgunKayit.class}, version = 2)
public abstract class KaloriTakipDatabase extends RoomDatabase {
    private static KaloriTakipDatabase kaloriTakipDatabase;

    public abstract KullaniciDao kullaniciDao();
    public abstract OgunKategoriDao ogunKategoriDao();
    public abstract BirimKategoriDao birimKategoriDao();
    public abstract OgunDao ogunDao();
    public abstract OgunKayitDao ogunKayitDao();

    private static final String databaseName = "cal.db";

    public static  final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE kullanici ADD COLUMN deviceId TEXT");
        }
    };
    public static KaloriTakipDatabase getKaloriTakipDatabase(Context context){
        if (kaloriTakipDatabase == null) {
            kaloriTakipDatabase = Room.databaseBuilder(context, KaloriTakipDatabase.class, databaseName).addMigrations(MIGRATION_1_2).allowMainThreadQueries().build();
        }
        return kaloriTakipDatabase;
    }
}
