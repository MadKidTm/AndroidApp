package com.gsb.alexa.gsbappli;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.gsb.alexa.gsbappli.Classes.MedecinDAO;
import com.gsb.alexa.gsbappli.R;

/**
 * Created by Alexa on 04/04/2018.
 */

public class DoctorDetailActivity extends Activity {

    long id;

    ImageView photo = null;
    TextView nom = null;
    TextView prenom = null;
    TextView num = null;
    TextView adresse = null;
    TextView specomp = null;
    ImageButton deleteMedecin = null;

    Context context = this;

    @Override
    public void onCreate(Bundle SavedInstanceBundle){
        super.onCreate(SavedInstanceBundle);
        setContentView(R.layout.repertoire_details);

        MedecinDAO medecinDAO = new MedecinDAO(this);

        //photo = (ImageView)findViewById(R.id.rep_det_img);
        nom = (TextView)findViewById(R.id.rep_det_lastname);
        prenom = (TextView)findViewById(R.id.rep_det_firstname);
        num = (TextView)findViewById(R.id.rep_det_num);
        adresse = (TextView)findViewById(R.id.rep_det_adress);
        specomp = (TextView) findViewById(R.id.rep_det_specomp);
        deleteMedecin = (ImageButton)findViewById(R.id.deleteMedecin);

        Intent intent = getIntent();
        id = intent.getLongExtra("idMedecin", 0);

        System.out.println("id vaut : "+id);
        String[] medecin;

        medecin = medecinDAO.getMedecinByID(id);

        nom.setText(medecin[1]);
        prenom.setText(medecin[2]);
        num.setText(medecin[3]);
        adresse.setText(medecin[4]);

        if(medecin[5].length() == 0)
            specomp.setText("aucune spécialité");
        else
            specomp.setText(medecin[5]);




        deleteMedecin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert = new AlertDialog.Builder(context);

                alert.setMessage("Voulez vous vraiment supprimer ce medecin ?");
                alert.setTitle("Attention");
                alert.setIcon(R.drawable.warning_icon);

                alert.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        MedecinDAO medecinDAO = new MedecinDAO(context);

                        medecinDAO.deleteLigne(id);

                        Intent intent = new Intent (DoctorDetailActivity.this, DoctorActivity.class);
                        startActivity(intent);

                    }
                });

                alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog dialog = alert.create();
                dialog.show();

            }
        });




    }
}
