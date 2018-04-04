package com.gsb.alexa.gsbappli.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by Alexa on 24/03/2018.
 */

public class FamilleDAO extends DAOBaseGSB {

    public static final String KEY = "id";
    public static final String TITLE = "libelle";

    public static final String TABLE_NAME = "famille";
    public static final String TABLE_CREATE =
            "CREATE TABLE "+ TABLE_NAME +" ( "+
                    KEY +" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, "+
                    TITLE +" VARCHAR NOT NULL );";

    public FamilleDAO ( Context pContext){
        super(pContext);
        super.open();
    }

    public void insertFamille(String libelle){

        ContentValues values = new ContentValues();
        values.put(TITLE, libelle);
        getDb().insert(TABLE_NAME,null, values);
    }

    public void selectFamille(){
        Cursor c = getDb().rawQuery("SELECT * FROM "+TABLE_NAME, null );

        while (c.moveToNext()){
            System.out.println(c.getLong(0));
            System.out.println(c.getString(1));

        }

        c.close();
    }

    public long getIdFamille(String famille){

        Cursor c = getDb().rawQuery("SELECT "+KEY+" FROM "+ TABLE_NAME +
                                        " WHERE "+ TITLE +" = ?", new String[] {famille});
        if(c.moveToNext())
            return c.getLong(0);
        else
            return 0;
    }


}
