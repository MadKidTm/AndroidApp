package com.gsb.alexa.gsbappli;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.gsb.alexa.gsbappli.Classes.MedecinDAO;
import com.gsb.alexa.gsbappli.Classes.RepertoireAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alexa on 21/03/2018.
 */

public class DoctorActivity extends Activity {

    Button add = null;
    ListView listeMedecins = null;


    private String[] mMedecin;

    private HashMap[] medecins;
    int lignes;

    Context context = this;

    int index = -1;



    @Override
    public void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.gsb_medecin_mannagement);

        add = (Button)findViewById(R.id.doctorAdd);
        listeMedecins = (ListView)findViewById(R.id.doctorList);


        MedecinDAO medecinDAO = new MedecinDAO(this);
        Cursor c = medecinDAO.selectLignes();

        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

        HashMap<String,String> medecin;

        while(c.moveToNext()){
            medecin = new HashMap<String, String>();
            medecin.put("id", String.valueOf(c.getLong(0)));
            medecin.put("img", String.valueOf(R.drawable.icone_contact));
            medecin.put("nom", c.getString(1) +" "+c.getString(2));
            medecin.put("adresse", c.getString(3));
            medecin.put("tel", c.getString(4));
            medecin.put("spe_comp", c.getString(5));
            listItem.add(medecin);
        }





        listeMedecins.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent i = new Intent(DoctorActivity.this, )
                //index = i;
                //TranslateAnimation animation = new TranslateAnimation(0.0f, -(listeMedecins.getWidth()), 0.0f, 0.0f);
                //animation.setDuration(500);
                //animation.setInterpolator(new DecelerateInterpolator());
                //listeMedecins.getChildAt(i).setAnimation(animation);
                //animation.start();
                //animation.setAnimationListener(new Animation.AnimationListener() {
            }
        });


        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
        SimpleAdapter contactMedecin = new SimpleAdapter (context, listItem, R.layout.repertoire_item,
                new String[] {"img", "nom", "tel"},
                new int[] {R.id.img, R.id.nom, R.id.tel});

        //On attribut à notre listView l'adapter que l'on vient de créer
        listeMedecins.setAdapter(contactMedecin);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DoctorActivity.this, AddDoctorActivity.class );
                startActivity(i);

            }
        });



        //listeMedecins.setAdapter(new RepertoireAdapter());


    }

    public void onResume (){
        super.onResume();

        MedecinDAO medecinDAO = new MedecinDAO(this);
        Cursor c = medecinDAO.selectLignes();

        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

        HashMap<String,String> medecin;

        while(c.moveToNext()){
            medecin = new HashMap<String, String>();
            medecin.put("img", String.valueOf(R.drawable.icone_contact));
            medecin.put("nom", c.getString(1) +" "+c.getString(2));
            medecin.put("adresse", c.getString(3));
            medecin.put("tel", c.getString(4));
            medecin.put("spe_comp", c.getString(5));
            listItem.add(medecin);
        }

        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
        SimpleAdapter contactMedecin = new SimpleAdapter (context, listItem, R.layout.repertoire_item,
                new String[] {"img", "nom", "tel"},
                new int[] {R.id.img, R.id.nom, R.id.tel});

        //On attribut à notre listView l'adapter que l'on vient de créer
        listeMedecins.setAdapter(contactMedecin);


    }
}
