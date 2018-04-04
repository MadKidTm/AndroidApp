package com.gsb.alexa.gsbappli;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.gsb.alexa.gsbappli.Classes.MedecinDAO;
import com.gsb.alexa.gsbappli.Classes.MedicamentDAO;
import com.gsb.alexa.gsbappli.Classes.OffrirDAO;
import com.gsb.alexa.gsbappli.Classes.RapportDAO;
import com.gsb.alexa.gsbappli.Classes.Visiteur;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Alexa on 21/03/2018.
 */

public class ReportActivity extends Activity {

    Button nouveauRapport = null;

    ListView listeRapports = null;

    Visiteur visiteur ;

    Context context = this;


    @Override
    public void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.gsb_rapports);
        Intent i = getIntent();

        Date date;
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/YYYY");

        //OffrirDAO offrirDAO = new OffrirDAO(context);
        //offrirDAO.selectLignes();

        visiteur = i.getParcelableExtra("com.gsb.alexa.gsbappli.Classes.Visiteur");


        nouveauRapport = (Button)findViewById(R.id.nouveau_rapport);
        listeRapports = (ListView)findViewById(R.id.rapport_list) ;

        date = new Date();
        String aujourdhui = formater.format(date);

        RapportDAO rapportDAO = new RapportDAO(context);
        Cursor c = rapportDAO.getAllRapports();

        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

        HashMap<String,String> rapport;

        while(c.moveToNext()){
            rapport = new HashMap<String, String>();
            rapport.put("id", c.getString(0));
            rapport.put("date", c.getString(1));
            rapport.put("motif", c.getString(2));
            listItem.add(rapport);
        }
        c.close();

        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
        SimpleAdapter liste = new SimpleAdapter (context, listItem, R.layout.rapport_item,
                new String[] {"id", "date", "motif"},
                new int[] {R.id.rappotID ,R.id.date, R.id.motif});

        //On attribut à notre listView l'adapter que l'on vient de créer
        listeRapports.setAdapter(liste);


        nouveauRapport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //if(medecinDAO.isEmpty()){
                  //  Toast.makeText(context, "Vous devez ajoutez au moins un médecin avant de créer un rapport", Toast.LENGTH_LONG).show();
                //}else {

                    Intent i = new Intent(ReportActivity.this, AddRepportActivity.class);
                    i.putExtra("com.gsb.alexa.gsbappli.Classes.Visiteur", visiteur);
                    startActivity(i);
                //}


            }
        });





    }
}
