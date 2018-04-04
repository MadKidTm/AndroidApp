package com.gsb.alexa.gsbappli;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gsb.alexa.gsbappli.Classes.Medecin;
import com.gsb.alexa.gsbappli.Classes.MedecinDAO;

/**
 * Created by Alexa on 24/03/2018.
 */

public class AddDoctorActivity extends Activity {

    private EditText nom = null;
    private EditText prenom = null;
    private EditText adresse = null;
    private EditText tel = null;
    private EditText speComp = null;
    private EditText departement = null;

    private Button send = null;

    Medecin medecin = null;

    Context context = this;


    public void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.gsb_add_medecin);

        nom = (EditText)findViewById(R.id.doc_nom);
        prenom = (EditText)findViewById(R.id.doc_prenom);
        adresse = (EditText)findViewById(R.id.doc_adresse);
        tel = (EditText)findViewById(R.id.doc_tel);
        speComp = (EditText)findViewById(R.id.doc_spe_comp);
        departement = (EditText)findViewById(R.id.doc_departement);

        nom.addTextChangedListener(textWatcher);
        prenom.addTextChangedListener(textWatcher);
        adresse.addTextChangedListener(textWatcher);
        tel.addTextChangedListener(textWatcher);
        speComp.addTextChangedListener(textWatcher);
        departement.addTextChangedListener(textWatcher);

        send = (Button)findViewById(R.id.doc_send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                medecin = new Medecin();
                medecin.setNom(nom.getText().toString());
                medecin.setPrenom(prenom.getText().toString());
                medecin.setAdresse(adresse.getText().toString());
                medecin.setTel(tel.getText().toString());
                medecin.setSpecialite_comp(speComp.getText().toString());
                medecin.setDepartement(departement.getText().toString());




                if(medecin.getNom().length() == 0 || medecin.getPrenom().length() == 0 || medecin.getTel().length() == 0 || medecin.getAdresse().length() == 0 || medecin.getDepartement().length() == 0 ){
                    Toast.makeText(context, "Certains champs obligatoire ne sont pas remplis", Toast.LENGTH_LONG).show();
                } else {

                    MedecinDAO medecinDao = new MedecinDAO(context);




                    if (medecinDao.medecinExiste(medecin.getNom(), medecin.getPrenom())) {

                        Toast.makeText(context, "Ce medecin existe deja.", Toast.LENGTH_LONG).show();

                    } else {

                        medecinDao.insertLigne(medecin.getNom(),
                                medecin.getPrenom(),
                                medecin.getTel(),
                                medecin.getTel(),
                                medecin.getSpecialite_comp(),
                                medecin.getDepartement());


                        Toast.makeText(context, "Nouveau medecin enregistré avec succès", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


    }

    public TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
