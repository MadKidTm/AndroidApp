package com.gsb.alexa.gsbappli;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
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

    Spinner dateSelect = null;

    Visiteur visiteur ;

    Context context = this;


    @Override
    public void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.gsb_rapports);
        Intent i = getIntent();

        visiteur = i.getParcelableExtra("com.gsb.alexa.gsbappli.Classes.Visiteur");


        nouveauRapport = (Button)findViewById(R.id.nouveau_rapport);
        listeRapports = (ListView)findViewById(R.id.rapport_list) ;
        dateSelect = (Spinner)findViewById(R.id.repport_date_select);


    }

    public void onResume(){
        super.onResume();

        RapportDAO rapportDAO = new RapportDAO(this);

        Cursor c = rapportDAO.getAllRapports();

        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

        HashMap<String,String> rapport;

        while(c.moveToNext()){
            rapport = new HashMap<String, String>();
            rapport.put("id", c.getString(0));
            rapport.put("date", c.getString(1));
            rapport.put("motif", c.getString(2));
            listItem.add(rapport);

            System.out.println("motif vaut : "+c.getString(2));
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


                Intent i = new Intent(ReportActivity.this, AddRepportActivity.class);
                i.putExtra("com.gsb.alexa.gsbappli.Classes.Visiteur", visiteur);
                startActivity(i);

            }
        });

        listeRapports.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                long id;
                HashMap<String, String> rapport = (HashMap<String, String >)adapterView.getItemAtPosition(i);
                id = Long.valueOf(rapport.get("id"));

                Intent intent = new Intent(ReportActivity.this, ReportDetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);

            }
        });


    }
}
