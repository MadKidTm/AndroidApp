package com.gsb.alexa.gsbappli.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by Alexa on 23/03/2018.
 */

public class VisiteurDAO extends DAOBaseGSB {

    public static final String KEY = "id";
    public static final String LAST_NAME = "nom";
    public static final String NAME = "prenom";
    public static final String LOGIN = "login";
    public static final String PW = "mdp";
    public static final String ADDRESS = "adresse";
    public static final String ZIP_CODE = "cp";
    public static final String CITY = "ville";
    public static final String HIRING_DATE = "date_embauche";

    public static final String TABLE_NAME = "visiteur";
    public static final String VISITEUR_TABLE_CREATE =
            "CREATE TABLE "+ TABLE_NAME +" ( "+
                    KEY +" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, "+
                    LAST_NAME +" VARCHAR NOT NULL, "+
                    NAME +" VARCHAR NOT NULL, "+
                    LOGIN +" VARCHAR, "+
                    PW +" VARCHAR, "+
                    ADDRESS +" VARCHAR, "+
                    ZIP_CODE +" NUMERIC (5), "+
                    CITY +" VARCHAR, "+
                    HIRING_DATE +" DATETIME );";

    public static final String VISITEUR_TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME +";";



    public VisiteurDAO (Context pContext){
        super(pContext);
        super.open();
        System.out.println("Base de données ouverte");
    }

    public void insertLigne(String nom, String prenom, String login, String pw, String adresse, String code_postal, String ville, String date_embauche){

        ContentValues values = new ContentValues();
        values.put(LAST_NAME, nom);
        values.put(NAME, prenom);
        values.put(LOGIN, login);
        values.put(PW, pw);
        values.put(ADDRESS, adresse);
        values.put(ZIP_CODE, code_postal);
        values.put(CITY, ville);
        values.put(HIRING_DATE, date_embauche);
        getDb().insert(TABLE_NAME, null, values );

        System.out.println("Insert effectué");
    }

    public void selectLignes(){
        Cursor c = getDb().rawQuery("SELECT * FROM "+TABLE_NAME, null);

        while (c.moveToNext()){
            System.out.print(c.getString(0)+"\n");
            System.out.print(c.getString(1)+"\n");
            System.out.print(c.getString(2)+"\n");
            System.out.print(c.getString(3)+"\n");
            System.out.print(c.getString(4)+"\n");
            System.out.print(c.getString(5)+"\n");
            System.out.print(c.getString(6)+"\n");
            System.out.print(c.getString(7)+"\n");
            System.out.print(c.getString(8)+"\n");
        }
    }

    public String[] selectLigne(String login, String pw){
        Cursor c = getDb().rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+LOGIN+" = ? AND "+ PW+" = ?", new String[] {login, pw});

        String[] visiteur = new String[9];

        while (c.moveToNext()){
            visiteur[0] = c.getString(0);
            visiteur[1] = c.getString(1);
            visiteur[2] = c.getString(2);
            visiteur[3] = c.getString(3);
            visiteur[4] = c.getString(4);
            visiteur[5] = c.getString(5);
            visiteur[6] = c.getString(6);
            visiteur[7] = c.getString(7);
            visiteur[8] = c.getString(8);
        }

        return visiteur;
    }

    public boolean isConnectionOK(String login, String pw){

        Cursor c = getDb().rawQuery("SELECT * FROM "+ TABLE_NAME +" WHERE "+LOGIN+ " = ? AND "+PW+" = ?",
                                    new String[] {login,pw});

        if(c.moveToNext()){
            c.close();
            return true;
        }
        c.close();
        return false;
    }

    public long getIdVisiteur(String login, String pw){
        long id = 0;

        Cursor c = getDb().rawQuery("SELECT "+KEY+
                                        " FROM "+TABLE_NAME+
                                        " WHERE "+ LOGIN+" = ? AND "+PW+" = ?", new String[] {login, pw});

        while (c.moveToNext()) {
            c.close();
            id = c.getLong(0);
        }

        c.close();

        return id;

    }



}
