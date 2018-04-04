package com.gsb.alexa.gsbappli.Classes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Alexa on 23/03/2018.
 */

public abstract class DAOBaseGSB {

    // Nous sommes à la première version de la base
    // Si je décide de la mettre à jour, il faudra changer cet attribut
    protected final static int VERSION = 5;
    // Le nom du fichier qui représente ma base
    protected final static String NOM = "GSB.db";

    protected SQLiteDatabase mDb = null;
    protected Database mHandler = null;

    public DAOBaseGSB(Context pContext) {
        this.mHandler = new Database(pContext, NOM, null, VERSION);
    }

    public SQLiteDatabase open() {

        // Pas besoin de fermer la dernière base puisque getWritableDatabase s'en charge
        mDb = mHandler.getWritableDatabase();

        return mDb;
    }

    public void close() {
        mDb.close();
    }

    public  SQLiteDatabase getDb() {
        return mDb;
    }
}
