package com.gsb.alexa.gsbappli.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gsb.alexa.gsbappli.DoctorActivity;

import java.nio.channels.SelectableChannel;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alexa on 23/03/2018.
 */

public class MedecinDAO extends DAOBaseGSB {

    public static final String KEY = "id";
    public static final String LAST_NAME = "nom";
    public static final String NAME = "prenom";
    public static final String ADDRESS = "adresse";
    public static final String PHONE = "tel";
    public static final String COMP_SPE = "specialite_complementaire";
    public static final String DEPARTMENT = "departement";

    public static final String TABLE_NAME = "medecin";
    public static final String TABLE_CREATE =
            "CREATE TABLE "+ TABLE_NAME + "( "+
                    KEY +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,"+
                    LAST_NAME +" VARCHAR (20) NOT NULL, "+
                    NAME +" VARCHAR NOT NULL, "+
                    ADDRESS +" VARCHAR, "+
                    PHONE +" VARCHAR, "+
                    COMP_SPE +" VARCHAR, "+
                    DEPARTMENT +" VARCHAR );";

    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME +";";


    public MedecinDAO(Context pContext) {
        super(pContext);
        super.open();
    }

    public String[] getMedecinByID(long id){

        Cursor c = getDb().rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY+" = ? ", new String[] {String.valueOf(id)}) ;
        String[] medecin = new String[7] ;

        while (c.moveToNext()){
            medecin[0] = String.valueOf(c.getLong(0)) ;
            medecin[1] = c.getString(1) ;
            medecin[2] = c.getString(2) ;
            medecin[3] = c.getString(3) ;
            medecin[4] = c.getString(4) ;
            medecin[5] = c.getString(5) ;
            medecin[6] = c.getString(6) ;
        }

        c.close();
        return medecin ;
    }

    public void insertLigne (String nom, String prenom, String adresse, String tel, String comp_spe, String departement){

        ContentValues c = new ContentValues();
        c.put(LAST_NAME, nom);
        c.put(NAME, prenom);
        c.put(ADDRESS, adresse);
        c.put(PHONE, tel);
        c.put(COMP_SPE, comp_spe);
        c.put(DEPARTMENT, departement);
        getDb().insert(TABLE_NAME,null, c);
    }

    public void deleteLigne (long id){
        getDb().delete(TABLE_NAME, KEY+" = ?", new String[] {String.valueOf(id)});
        System.out.print("\n La table d'id "+String.valueOf(id)+" a été suprimé \n");
    }

    public Cursor selectLignes(){

        Cursor c = mDb.rawQuery("SELECT *" +
                                    "FROM "+ TABLE_NAME, null);

        return c;
    }

    public boolean isEmpty(){

        Cursor c = mDb.rawQuery("SELECT *" +
                "FROM "+ TABLE_NAME, null);

        if (c.moveToNext())
            return false;
        else
            return true;

    }

    public void deleteLigne(String nom, String prenom){
        getDb().delete(TABLE_NAME, LAST_NAME+ " = ? AND "+ NAME +" = ?", new String[] {nom, prenom});
        System.out.println("ligne de la table "+TABLE_NAME+ " ou nom = "+nom+" et prenom = "+ prenom +" correctement supprimé");
    }

    public boolean medecinExiste(String nom, String prenom){

        Cursor c = getDb().rawQuery("SELECT * " +
                                        "FROM "+ TABLE_NAME +
                                        " WHERE "+ LAST_NAME +" = ? AND "+NAME+" = ?", new String[] {nom, prenom});

        if (c.moveToNext()) {

            c.close();
            return true;

        }
        return false;
    }

    public void getMedecins(ArrayList<String> medecins){



        Cursor c = getDb().rawQuery("SELECT "+LAST_NAME+", "+NAME+" "+
                                        "FROM "+TABLE_NAME, null);

        while (c.moveToNext()){

            medecins.add(c.getString(0)+ " "+c.getString(1));
        }

        c.close();


    }

    public int getNumberOfLines(){
        int lines = 0;

        Cursor c = getDb().rawQuery("SELECT * FROM "+TABLE_NAME, null);

        while(c.moveToNext()){
            lines++;
        }

        c.close();

        return lines;
    }

    public long getIdMedecin(String nom, String prenom){
        long id = 0;

        Cursor c = getDb().rawQuery("SELECT "+ KEY +
                                        " FROM "+TABLE_NAME+
                                        " WHERE "+LAST_NAME+" = ? AND "+NAME+" = ?", new String[] {nom, prenom});

        while(c.moveToNext()){
            id = c.getLong(0);
        }

        c.close();

        return id;
    }


}
