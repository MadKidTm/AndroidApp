package com.gsb.alexa.gsbappli.Classes;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.*;


/**
 * Created by Alexa on 22/03/2018.
 */

public class Database extends SQLiteOpenHelper {

    public static final String MEDECIN_KEY = "id";
    public static final String MEDECIN_LAST_NAME = "nom";
    public static final String MEDECIN_NAME = "prenom";
    public static final String MEDECIN_ADDRESS = "adresse";
    public static final String MEDECIN_PHONE = "tel";
    public static final String MEDECIN_COMP_SPE = "specialite_complementaire";
    public static final String MEDECIN_DEPARTMENT = "departement";



    public static final String MEDECIN_TABLE_NAME = "medecin";
    public static final String MEDECIN_TABLE_CREATE =
            "CREATE TABLE "+ MEDECIN_TABLE_NAME + "( "+
                    MEDECIN_KEY +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,"+
                    MEDECIN_LAST_NAME +" VARCHAR (20) NOT NULL, "+
                    MEDECIN_NAME +" VARCHAR NOT NULL, "+
                    MEDECIN_ADDRESS +" VARCHAR, "+
                    MEDECIN_PHONE +" VARCHAR, "+
                    MEDECIN_COMP_SPE +" VARCHAR, "+
                    MEDECIN_DEPARTMENT +" VARCHAR );";


    public static final String VISITEUR_KEY = "id";
    public static final String VISITEUR_LAST_NAME = "nom";
    public static final String VISITEUR_NAME = "prenom";
    public static final String VISITEUR_LOGIN = "login";
    public static final String VISITEUR_PW = "mdp";
    public static final String VISITEUR_ADDRESS = "adresse";
    public static final String VISITEUR_ZIP_CODE = "cp";
    public static final String VISITEUR_CITY = "ville";
    public static final String VISITEUR_HIRING_DATE = "date_embauche";

    public static final String VISITEUR_TABLE_NAME = "visiteur";
    public static final String VISITEUR_TABLE_CREATE =
            "CREATE TABLE "+ VISITEUR_TABLE_NAME +" ( "+
                    VISITEUR_KEY +" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, "+
                    VISITEUR_LAST_NAME +" VARCHAR NOT NULL, "+
                    VISITEUR_NAME +" VARCHAR NOT NULL, "+
                    VISITEUR_LOGIN +" VARCHAR, "+
                    VISITEUR_PW +" VARCHAR, "+
                    VISITEUR_ADDRESS +" VARCHAR, "+
                    VISITEUR_ZIP_CODE +" VARCHAR, "+
                    VISITEUR_CITY +" VARCHAR, "+
                    VISITEUR_HIRING_DATE +" VARCHAR );";

    public static final String RAPPORT_KEY = "id";
    public static final String RAPPORT_DATE = "date";
    public static final String RAPPORT_REASON = "motif";
    public static final String RAPPORT_RESULT = "bilan";
    public static final String RAPPORT_DOCTOR = "idMedecin";
    public static final String RAPPORT_VISITEUR = "idVisiteur";

    public static final String RAPPORT_TABLE_NAME = "rapport";
    public static final String RAPPORT_TABLE_CREATE =
            "CREATE TABLE "+ RAPPORT_TABLE_NAME +" ( "+
                    RAPPORT_KEY +" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, "+
                    RAPPORT_DATE +" DATE, "+
                    RAPPORT_REASON +" VARCHAR, "+
                    RAPPORT_RESULT +" VARCHAR, "+
                    RAPPORT_DOCTOR +" REFERENCES medecin (id) ON DELETE CASCADE ON UPDATE CASCADE MATCH SIMPLE, "+
                    RAPPORT_VISITEUR +" INTEGER NOT NULL REFERENCES visiteur (id) ); ";


    public static final String FAMILLE_KEY = "id";
    public static final String FAMILLE_TITLE = "libelle";

    public static final String FAMILLE_TABLE_NAME = "famille";
    public static final String FAMILLE_TABLE_CREATE =
            "CREATE TABLE "+ FAMILLE_TABLE_NAME +" ( "+
                    FAMILLE_KEY +" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, "+
                    FAMILLE_TITLE +" VARCHAR NOT NULL );";


    public static final String MEDICAMENT_KEY = "id";
    public static final String MEDICAMENT_NAME = "nom_commercial";
    public static final String MEDICAMENT_FAMILLE = "idFamille";
    public static final String MEDICAMENT_COMPOSITION = "composition";
    public static final String MEDICAMENT_EFFECTS = "effets";
    public static final String MEDICAMENT_CONTRE_INDIC = "contre_indications";

    public static final String MEDICAMENT_TABLE_NAME = "medicament";
    public static final String MEDICAMENT_TABLE_CREATE =
            "CREATE TABLE "+ MEDICAMENT_TABLE_NAME +" ( "+
                    MEDICAMENT_KEY +" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, "+
                    MEDICAMENT_NAME +" VARCHAR NOT NULL, "+
                    MEDICAMENT_FAMILLE +" INTEGER REFERENCES famille (id), "+
                    MEDICAMENT_COMPOSITION +" VARCHAR, "+
                    MEDICAMENT_EFFECTS +" VARCHAR, "+
                    MEDICAMENT_CONTRE_INDIC +" VARCHAR );";


    public static final String OFFER_MEDICINE_KEY = "idMedicament";
    public static final String OFFER_RAPPORT_KEY = "idRapport";
    public static final String OFFER_AMOUNT = "quantite";

    public static final String OFFER_TABLE_NAME = "offrir";
    public static final String OFFER_TABLE_CREATE =
            "CREATE TABLE "+ OFFER_TABLE_NAME + " ( "+
                    OFFER_MEDICINE_KEY +" INTEGER REFERENCES medicament (id), "+
                    OFFER_RAPPORT_KEY +" INTEGER REFERENCES rapport (id), "+
                    OFFER_AMOUNT +" INTEGER, "+
                    "PRIMARY KEY (idMedicament, idRapport) );";


    public static final String MEDECIN_TABLE_DROP = "DROP TABLE IF EXISTS " + MEDECIN_TABLE_NAME +";";
    public static final String VISITEUR_TABLE_DROP = "DROP TABLE IF EXISTS " + VISITEUR_TABLE_NAME +";";
    public static final String RAPPORT_TABLE_DROP = "DROP TABLE IF EXISTS " + RAPPORT_TABLE_NAME +";";
    public static final String MEDICAMENT_TABLE_DROP = "DROP TABLE IF EXISTS "+ MEDICAMENT_TABLE_NAME +";";
    public static final String FAMILLE_TABLE_DROP = "DROP TABLE IF EXISTS "+ FAMILLE_TABLE_NAME +";";
    public static final String OFFER_TABLE_DROP = "DROP TABLE IF EXISTS "+ OFFER_TABLE_NAME +";";




    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MEDECIN_TABLE_CREATE);
        db.execSQL(VISITEUR_TABLE_CREATE);
        db.execSQL(RAPPORT_TABLE_CREATE);
        db.execSQL(MEDICAMENT_TABLE_CREATE);
        db.execSQL(FAMILLE_TABLE_CREATE);
        db.execSQL(OFFER_TABLE_CREATE);

        ContentValues values = new ContentValues();





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(MEDECIN_TABLE_DROP);
        db.execSQL(VISITEUR_TABLE_DROP);
        db.execSQL(RAPPORT_TABLE_DROP);
        db.execSQL(MEDICAMENT_TABLE_DROP);
        db.execSQL(FAMILLE_TABLE_DROP);
        db.execSQL(OFFER_TABLE_DROP);

        onCreate(db);
        System.out.print("la base a été crée");
    }

    @Override
    public void onOpen(SQLiteDatabase db){


    }
}
