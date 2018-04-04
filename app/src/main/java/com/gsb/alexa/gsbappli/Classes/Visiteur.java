package com.gsb.alexa.gsbappli.Classes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alexa on 24/03/2018.
 */

public class Visiteur implements Parcelable {

    private long id;
    private String nom;
    private String prenom;
    private String login;
    private String password;
    private String adresse;
    private String codePostal;
    private String ville;
    private String dateEmbauche;



    public Visiteur(long id, String nom, String prenom, String login, String password, String adresse, String codePostal, String ville, String dateEmbauche){

        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.dateEmbauche = dateEmbauche;

    }

    public long getId(){
        return id;
    }

    public void setId(long newId){
        id = newId;
    }

    public String getNom(){
        return nom;
    }

    public void setNom(String newNom){
        nom = newNom;
    }

    public String getPrenom(){
        return prenom;
    }

    public void setPrenom(String newPrenom){
        prenom = newPrenom;
    }

    public String getLogin(){
        return login;
    }

    public void setLogin(String newLogin){
        login = newLogin;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword( String newPass){
        password = newPass;
    }

    public String getAdresse(){
        return adresse;
    }

    public void setAdresse(String newAdresse){
        adresse = newAdresse;
    }

    public String getCodePostal(){
        return codePostal;
    }

    public void setCodePostal(String newCode){
        codePostal = newCode;
    }

    public String getVille(){
        return ville;
    }

    public void setVille(String  newVille){
        ville = newVille;
    }

    public String getDateEmbauche(){
        return dateEmbauche;
    }

    public void setDateEmbauche(String newDateEmbauche){
        dateEmbauche = newDateEmbauche;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(nom);
        parcel.writeString(prenom);
        parcel.writeString(login);
        parcel.writeString(password);
        parcel.writeString(adresse);
        parcel.writeString(codePostal);
        parcel.writeString(ville);
        parcel.writeString(dateEmbauche);

    }

    public static final Parcelable.Creator<Visiteur> CREATOR = new Parcelable.Creator<Visiteur>() {


    @Override
    public Visiteur createFromParcel(Parcel source) {
        return new Visiteur(source);
    }

    @Override
    public Visiteur[] newArray(int size) {
        return new Visiteur[size];
    }
};

public Visiteur(Parcel in) {

        id = in.readLong();
        nom = in.readString();
        prenom = in.readString();
        login = in.readString();
        password = in.readString();
        adresse = in.readString();
        codePostal = in.readString();
        ville = in.readString();
        dateEmbauche = in.readString();
        }
}
