package com.gsb.alexa.gsbappli.Classes;

/**
 * Created by Alexa on 23/03/2018.
 */

public class Medecin {

    private long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private String specialite_comp;
    private String departement;

    public Medecin(){


        nom = null;
        prenom = null;
        adresse = null;
        tel = null;
        specialite_comp = null;
        departement = null;
    }

    public Medecin (long id, String nom, String prenom, String adresse, String tel, String specialite_comp, String departement){
        super();
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.specialite_comp = specialite_comp;
        this.departement = departement;
    }

    public Medecin ( String nom, String prenom, String adresse, String tel, String specialite_comp, String departement){
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.specialite_comp = specialite_comp;
        this.departement = departement;
    }

    public long getId(){
        return id;
    }

    public void setId(long newId){
        this.id = newId;
    }

    public String getNom(){
        return nom;
    }

    public void setNom(String newNom){
        this.nom = newNom;
    }

    public String getPrenom(){
        return prenom;
    }

    public void setPrenom(String newPrenom){
        this.prenom = newPrenom;
    }

    public String getAdresse(){
        return adresse;
    }

    public void setAdresse(String newAdresse){
        this.adresse = newAdresse;
    }

    public String getTel(){
        return tel;
    }

    public void setTel(String newTel){
        this.tel = newTel;
    }

    public String getSpecialite_comp(){
        return specialite_comp;
    }

    public void setSpecialite_comp(String newSpeComp){
        this.specialite_comp = newSpeComp;
    }

    public String getDepartement(){
        return departement;
    }

    public void setDepartement(String newDepartement){
        this.departement = newDepartement;
    }
}
