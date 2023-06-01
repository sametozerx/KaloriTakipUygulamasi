package com.proje.kaloritakipuygulamasi.util;

import java.util.Calendar;
import java.util.Date;

public class TarihUtil {

    public static int getGun(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static int getAy(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(Calendar.MONTH) + 1;
    }

    public static int getYil(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(Calendar.YEAR);
    }
    public static String getDate(){
        String gun= Integer.toString(getGun());
        String ay= Integer.toString(getAy());
        String yil= Integer.toString(getYil());
        return gun+ay+yil;
    }
}
