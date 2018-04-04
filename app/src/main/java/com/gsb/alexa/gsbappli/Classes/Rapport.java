package com.gsb.alexa.gsbappli.Classes;

/**
 * Created by Alexa on 24/03/2018.
 */

public class Rapport {

    private long id;
    private long idMedecin;
    private long idVisiteur;
    private String date;
    private String motif;
    private String bilan;

    public Rapport(){
        date = null;
        motif = null;
        bilan = null;

    }

    public long getId(){
        return id;
    }

    public long getIdMedecin(){
        return idMedecin;
    }

    public void setIdMedecin(long newId){
        idMedecin = newId;
    }

    public long getIdVisiteur(){
        return idVisiteur;
    }

    public void setIdVisiteur(long newId){
        idVisiteur = newId;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String newDate){
        date = newDate;
    }

    public String getMotif(){
        return motif;
    }

    public void setMotif(String newMotif){
        motif = newMotif;
    }

    public String getBilan(){
        return bilan;
    }

    public void setBilan(String newBilan){
        bilan = newBilan;
    }


}


