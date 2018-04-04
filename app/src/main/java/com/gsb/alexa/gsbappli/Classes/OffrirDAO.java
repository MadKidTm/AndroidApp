package com.gsb.alexa.gsbappli.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by Alexa on 24/03/2018.
 */

public class OffrirDAO extends DAOBaseGSB {

    public static final String MEDICINE_KEY = "idMedicament";
    public static final String RAPPORT_KEY = "idRapport";
    public static final String AMOUNT = "quantite";

    public static final String TABLE_NAME = "offrir";
    public static final String OFFER_TABLE_CREATE =
            "CREATE TABLE "+ TABLE_NAME + " ( "+
                    MEDICINE_KEY +" INTEGER REFERENCES medicament (id), "+
                    RAPPORT_KEY +" INTEGER REFERENCES rapport (id), "+
                    AMOUNT +" INTEGER, "+
                    "PRIMARY KEY (idMedicament, idRapport) );";

    public OffrirDAO (Context pContext){
        super(pContext);
        super.open();
    }

    public void insertLigne(long idRapport, long idMedicament, String quantite){
        ContentValues values = new ContentValues();

        values.put(RAPPORT_KEY, idRapport);
        values.put(MEDICINE_KEY, idMedicament);
        values.put(AMOUNT, quantite);
        getDb().insert(TABLE_NAME, null, values);
    }

    public void selectLignes(){
        Cursor c = getDb().rawQuery("SELECT * FROM "+TABLE_NAME, null);

        while (c.moveToNext()){
            System.out.println("idMedicament = "+c.getLong(0));
            System.out.println("idRapport = "+c.getLong(1));
            System.out.println("quantité = "+ c.getString(2));
        }

        c.close();
    }

    public void deleteAll(){
        getDb().delete(TABLE_NAME, AMOUNT+">0", null);
    }


}
