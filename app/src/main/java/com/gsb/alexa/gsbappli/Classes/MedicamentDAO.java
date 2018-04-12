package com.gsb.alexa.gsbappli.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alexa on 24/03/2018.
 */

public class MedicamentDAO extends DAOBaseGSB {

    public static final String KEY = "id";
    public static final String NAME = "nom_commercial";
    public static final String FAMILLE = "idFamille";
    public static final String COMPOSITION = "composition";
    public static final String EFFECTS = "effets";
    public static final String CONTRE_INDIC = "contre_indications";

    public static final String TABLE_NAME = "medicament";
    public static final String MEDICAMENT_TABLE_CREATE =
            "CREATE TABLE "+ TABLE_NAME +" ( "+
                    KEY +" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, "+
                    FAMILLE +" INTEGER REFERENCES famille (id), "+
                    NAME +" VARCHAR NOT NULL, "+
                    COMPOSITION +" VARCHAR, "+
                    EFFECTS +" VARCHAR, "+
                    CONTRE_INDIC +" VARCHAR );";



    public MedicamentDAO (Context pContext){
        super(pContext);
        super.open();



    }

    public void insertLignes(String nom, long idFamille, String composition, String effets, String contreIndic){

        System.out.println("Entré dans la BDD");

        ContentValues values = new ContentValues();
        values.put(NAME, nom);
        values.put(FAMILLE, idFamille);
        values.put(COMPOSITION, composition);
        values.put(EFFECTS, effets);
        values.put(CONTRE_INDIC, contreIndic);
        getDb().insert(TABLE_NAME, null, values);

    }

    public void selectLignes(){

        Cursor c = getDb().rawQuery("SELECT * FROM "+TABLE_NAME, null);

        System.out.print("test");

        while( c.moveToNext()){
            System.out.println("id " + c.getLong(0));
            System.out.println(" nom " + c.getString(1));
            System.out.println(" idFamille " + c.getLong(2));
            System.out.println(" compo " + c.getString(3));
            System.out.println(" effets " + c.getString(4));
            System.out.println(" contre " + c.getString(5));

        }
        c.close();
    }

    public void deleteLigne(String id){
        getDb().delete(TABLE_NAME,KEY+" = ?",new String[] {id});
    }

    public void getMedicaments(ArrayList<String> medicaments){

        Cursor c = getDb().rawQuery("SELECT "+NAME+" FROM "+TABLE_NAME, null);

        while (c.moveToNext()){
            medicaments.add(c.getString(0));
        }
        c.close();
    }

    public long getMedicamentId(String nom){
        long result = 0;
        Cursor c = getDb().rawQuery("SELECT "+KEY+" FROM "+TABLE_NAME+" WHERE "+NAME+" = ?", new String[] {nom});

        while(c.moveToNext()){
            result = c.getLong(0);
        }
        c.close();

        return result;
    }

    public HashMap<String, String> getMedicamentById(long id){
        HashMap<String, String > medicament = new HashMap<String, String>();

        Cursor c = getDb().rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY+" = ?", new String[] {String.valueOf(id)});

        while ( c.moveToNext()){
            medicament.put("id", String.valueOf(c.getLong(0)));
            medicament.put("nom", c.getString(1));
            medicament.put("idFamille", String.valueOf(c.getLong(2)));
            medicament.put("composition", c.getString(3));
            medicament.put("effets", c.getString(4));
            medicament.put("contre_indications", c.getString(5));
        }

        c.close();

        return medicament;
    }


}
