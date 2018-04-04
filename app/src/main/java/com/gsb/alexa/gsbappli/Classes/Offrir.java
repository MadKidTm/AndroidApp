package com.gsb.alexa.gsbappli.Classes;

/**
 * Created by Alexa on 25/03/2018.
 */

public class Offrir {

    private long idMedicament;
    private long idRapport;
    String quantite;

    public Offrir(){
        idMedicament = 0;
        idRapport = 0;
        quantite = null;
    }

    public long getIdMedicament(){
        return idMedicament;
    }

    public void setIdMedicament(long id){
        idMedicament = id;
    }

    public long getIdRapport(){
        return idRapport;
    }

    public void setIdRapport( long id){
        idRapport = id;
    }

    public String getQuantite(){
        return quantite;
    }

    public void setQuantite(String newQuandtite){
        quantite = newQuandtite;
    }
}
