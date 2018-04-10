package com.gsb.alexa.gsbappli.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by Alexa on 24/03/2018.
 */

public class RapportDAO extends DAOBaseGSB{

    public static final String KEY = "id";
    public static final String DATE = "date";
    public static final String REASON = "motif";
    public static final String RESULT = "bilan";
    public static final String DOCTOR = "idMedecin";
    public static final String VISITEUR = "idVisiteur";

    public static final String TABLE_NAME = "rapport";
    public static final String TABLE_CREATE =
            "CREATE TABLE "+ TABLE_NAME +" ( "+
                    KEY +" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, "+
                    DATE +" DATE, "+
                    REASON +" VARCHAR, "+
                    RESULT +" VARCHAR, "+
                    DOCTOR +" REFERENCES medecin (id) ON DELETE NO ACTION ON UPDATE NO ACTION MATCH SIMPLE, "+
                    VISITEUR +" INTEGER NOT NULL REFERENCES visiteur (id) ); ";


    public RapportDAO (Context pContext){
        super(pContext);
        super.open();
    }

    public void insertLigne(String date, String motif, String bilan, long idMedecin, long idVisiteur){

        ContentValues values = new ContentValues();
        values.put(DATE, date);
        values.put(REASON, motif);
        values.put(RESULT, bilan);
        values.put(DOCTOR, idMedecin);
        values.put(VISITEUR, idVisiteur);
        getDb().insert(TABLE_NAME, null, values);


    }


    public Cursor getRapportsByDate(String date){
        Cursor c = getDb().rawQuery("SELECT "+KEY+" FROM "+TABLE_NAME+" WHERE "+DATE+" = ?", new String[] {date});

        while(c.moveToNext()){
            System.out.println(c.getLong(0));
        }
        c.close();

        return c;
    }

    public void deleteAll(){
        getDb().delete(TABLE_NAME, KEY+ " < 20", null);
    }

    public long getRapportId(String date, String result){
        long id = 0;
        System.out.println("Avant la requete");
        Cursor c = getDb().rawQuery("SELECT "+KEY+" FROM "+TABLE_NAME+" WHERE "+
                                    DATE+" = ? AND "+ RESULT+" = ? "
                                    , new String[] {date, result});

        while (c.moveToNext()){
            id = c.getLong(0);
            System.out.println("test test test = "+ c.getLong(0));

        }

        return id;
    }

    public void selectLignes(){
        Cursor c = getDb().rawQuery("SELECT * FROM "+ TABLE_NAME, null);

        while(c.moveToNext()){
            System.out.println(c.getString(1));
            System.out.println(c.getString(2));
            System.out.println(c.getString(3));
            System.out.println(c.getLong(4));
            System.out.println(c.getLong(5));

        }
    }

    public Cursor getAllRapports(){

        Cursor c = getDb().rawQuery("SELECT * FROM "+ TABLE_NAME , null);

        return c;

    }

    public void getDates(ArrayList<String> dates){
        Cursor c = getDb().rawQuery("SELECT DISTINCT "+DATE+" FROM "+ TABLE_NAME, null);

        while(c.moveToNext()){
            dates.add(c.getString(0));
        }

        c.close();
    }






}
