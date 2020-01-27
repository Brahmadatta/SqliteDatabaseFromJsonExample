package com.example.sccm.sqlitedbfromjsonexample;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class MobileCodesDatabase extends SQLiteOpenHelper {

    private static MobileCodesDatabase sInstance;

    public static final String DATABASE_NAME = "mobilecodes.db";

    //Creating the mobile data Table
    public static final String TABLE_MOBILE_DATA = "SEARCH";

    //column names of mobile data Table
    public static final String INCREMENT_ID = "increment_id";
    public static final String COUNTRY_NAME = "name";
    public static final String COUNTRY_DIAL_CODE = "dial_code";
    public static final String COUNTRY_CODE = "code";

    public static synchronized MobileCodesDatabase getInstance(Context context){
        if (sInstance == null){
            sInstance = new MobileCodesDatabase(context.getApplicationContext());
        }
        return sInstance;
    }

    public MobileCodesDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Create Search Table

        String CREATE_MOBILE_DATA_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_MOBILE_DATA + "("
                + INCREMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COUNTRY_NAME + " TEXT UNIQUE,"
                + COUNTRY_DIAL_CODE + " TEXT,"
                + COUNTRY_CODE + " TEXT"
                + ")";

        sqLiteDatabase.execSQL(CREATE_MOBILE_DATA_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public ArrayList getAllMobileData(){

        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + MobileCodesDatabase.TABLE_MOBILE_DATA + " ORDER BY " + MobileCodesDatabase.COUNTRY_NAME +  " ASC ",null);

        if (cursor.moveToFirst()){
            do {

                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(MobileCodesDatabase.COUNTRY_NAME,cursor.getString(cursor.getColumnIndex(MobileCodesDatabase.COUNTRY_NAME)));
                hashMap.put(MobileCodesDatabase.COUNTRY_DIAL_CODE,cursor.getString(cursor.getColumnIndex(MobileCodesDatabase.COUNTRY_DIAL_CODE)));
                hashMap.put(MobileCodesDatabase.COUNTRY_CODE,cursor.getString(cursor.getColumnIndex(MobileCodesDatabase.COUNTRY_CODE)));

                arrayList.add(hashMap);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return arrayList;
    }
}
