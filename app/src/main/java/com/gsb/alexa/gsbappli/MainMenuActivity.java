package com.gsb.alexa.gsbappli;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.gsb.alexa.gsbappli.Classes.FamilleDAO;
import com.gsb.alexa.gsbappli.Classes.MedicamentDAO;
import com.gsb.alexa.gsbappli.Classes.OffrirDAO;
import com.gsb.alexa.gsbappli.Classes.RapportDAO;
import com.gsb.alexa.gsbappli.Classes.Visiteur;

/**
 * Created by Alexa on 21/03/2018.
 */

public class MainMenuActivity extends Activity {

    Button rapport = null;
    Button medecin = null;
    Button deconnection = null;

    Visiteur visiteur;



    @Override
    public void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.gsb_main_menu);

        rapport = (Button)findViewById(R.id.rapportButton);
        medecin = (Button)findViewById(R.id.medecinButton);
        deconnection = (Button)findViewById(R.id.deco);

        Intent i = getIntent();

        visiteur = i.getParcelableExtra("com.gsb.alexa.gsbappli.Classes.Visiteur");

        System.out.println(visiteur.getNom());




        rapport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainMenuActivity.this, ReportActivity.class);
                i.putExtra("com.gsb.alexa.gsbappli.Classes.Visiteur", visiteur);
                startActivity(i);

            }
        });

        medecin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenuActivity.this, DoctorActivity.class);
                startActivity(i);

            }
        });

        deconnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenuActivity.this, MainActivity.class);
                startActivity(i);
            }
        });




    }


    public void OnClickListener(View v){


    };
}
