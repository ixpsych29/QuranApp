package com.example.quranmajeed;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String SURAH_TABLE = "tsurah";
    public static final String AYAH_TABLE = "tayah";
    public static final String ARABIC_COLUMN = "Arabic Text";



    public DBHelper(@Nullable Context context) {
        super(context, "MyDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public ArrayList<String>  getSurahList(){

        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select * from " + SURAH_TABLE ;
        Cursor cursor = db.rawQuery(Query, null);
        ArrayList<String> rtn=new ArrayList<String>();
        if(!(cursor.getCount() <= 0)){
            while(cursor.moveToNext()) {
                rtn.add(cursor.getString(0)+"    "+cursor.getString(2)+"    "+cursor.getString(4));
            }
        }

        cursor.close();
        db.close();
        return rtn;
    }
    public ArrayList<String>  getSurah(int suraId){

        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select \""+ARABIC_COLUMN+"\" from " + AYAH_TABLE+ " WHERE AyaId =0 or SuraID =" + suraId;
        Cursor cursor = db.rawQuery(Query, null);
        ArrayList<String> rtn=new ArrayList<String>();
        if(!(cursor.getCount() <= 0)){
            while(cursor.moveToNext()) {
                rtn.add(cursor.getString(0));
            }
        }

        cursor.close();
        db.close();
        return rtn;
    }

    public ArrayList<String> getParaList() {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select * from " + AYAH_TABLE ;
        Cursor cursor = db.rawQuery(Query, null);
        ArrayList<String> rtn=new ArrayList<String>();
        com.example.qurandatabaseapp.QDH qdh=new com.example.qurandatabaseapp.QDH();
        ArrayList<String> paraName = new ArrayList<>();
        paraName=qdh.GetParahNameEnglish();
        int i=0;

        while(i<paraName.size())
        {
            rtn.add((i+1)+"   "+paraName.get(i));
            i++;
        }

        cursor.close();
        db.close();
        return rtn;

    }

    public ArrayList<String> getPara(int paraId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select \""+ARABIC_COLUMN+"\" from " + AYAH_TABLE+ " WHERE AyaId =0 or ParaID =" + paraId;

        if(paraId==0)
        {
            Query = "Select \""+ARABIC_COLUMN+"\" from " + AYAH_TABLE+ " WHERE AyaId =0 or ParaID =" + paraId;
        }
        Cursor cursor = db.rawQuery(Query, null);
        ArrayList<String> rtn=new ArrayList<String>();
        if(!(cursor.getCount() <= 0)){
            while(cursor.moveToNext()) {
                rtn.add(cursor.getString(0));
            }
        }

        cursor.close();
        db.close();
        return rtn;
    }}