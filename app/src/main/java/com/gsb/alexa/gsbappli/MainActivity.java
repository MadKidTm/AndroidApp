package com.gsb.alexa.gsbappli;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gsb.alexa.gsbappli.Classes.DAOBaseGSB;
import com.gsb.alexa.gsbappli.Classes.Database;
import com.gsb.alexa.gsbappli.Classes.FamilleDAO;
import com.gsb.alexa.gsbappli.Classes.MedecinDAO;
import com.gsb.alexa.gsbappli.Classes.MedicamentDAO;
import com.gsb.alexa.gsbappli.Classes.RapportDAO;
import com.gsb.alexa.gsbappli.Classes.Visiteur;
import com.gsb.alexa.gsbappli.Classes.VisiteurDAO;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends Activity {

    private Button login = null;
    private EditText id = null;
    private EditText password = null;
    private TextView idInfo = null;
    private TextView passwordInfo = null;

    private Context context = this;

    private String[] tableauVisiteur = new String[9];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        login = findViewById(R.id.login);
        id = findViewById(R.id.id);
        password = findViewById(R.id.password);
        idInfo = (TextView)findViewById(R.id.idInfo);
        passwordInfo = (TextView)findViewById(R.id.passwordInfo);


        password.addTextChangedListener(textWatcher);
        id.addTextChangedListener(textWatcher);

        FamilleDAO familleDAO = new FamilleDAO(context);
        MedicamentDAO medicamentDAO = new MedicamentDAO(this);




        //familleDAO.insertFamille("antalgique");
        //familleDAO.insertFamille("anti-inflamatoire");
        //familleDAO.insertFamille("antibiotique");

        //medicamentDAO.insertLignes("doliprane",familleDAO.getIdFamille("antalgique"),"paracetamol", "traitement douleurs", "enfant de moins de 6ans" );
        //medicamentDAO.insertLignes("neurofen",familleDAO.getIdFamille("anti-inflamatoire"),"ibuprofene", "traitement fièvre", "émoragie gastro intestinale" );
        //medicamentDAO.insertLignes("orelox",familleDAO.getIdFamille("antibiotique"),"cefpodoxime", "traitement sinusite aigue", "allergie betalactamines" );



        //familleDAO.selectFamille();
        //medicamentDAO.selectLignes();

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                VisiteurDAO visiteurDAO = new VisiteurDAO(context);
                Visiteur visiteur ;

                //visiteurDAO.insertLigne("TOMASIA", "Alex","talex", "buga1994", "16 avenue du trident", "06300", "Nice", "2018-03-20");




                String idText = id.getText().toString();
                String pwText = password.getText().toString();

                System.out.println(idText);
                System.out.println(pwText);

                //System.out.println("visiteur : "+ visiteur.getNom()+" "+visiteur.getPrenom());

                if(idText.length() == 0){

                    Toast.makeText(context, "Veuillez entrer votre identifiant", Toast.LENGTH_LONG).show();
                }

                if(idText.length() != 0 && pwText.length() != 0){
                    if(visiteurDAO.isConnectionOK(idText,pwText)){

                        tableauVisiteur = visiteurDAO.selectLigne(idText, pwText);

                        visiteur = new Visiteur(Long.valueOf(tableauVisiteur[0]),
                                tableauVisiteur[1],
                                tableauVisiteur[2],
                                tableauVisiteur[3],
                                tableauVisiteur[4],
                                tableauVisiteur[5],
                                tableauVisiteur[6],
                                tableauVisiteur[7],
                                tableauVisiteur[8]);

                        id.setText("");
                        password.setText("");

                        Intent i = new Intent(MainActivity.this, MainMenuActivity.class);
                        i.putExtra("com.gsb.alexa.gsbappli.Classes.Visiteur", visiteur);
                        startActivity(i);
                    }
                }
                //Intent i = new Intent(MainActivity.this, MainMenuActivity.class);
                //startActivity(i);
            }



        });
    }

    private TextWatcher textWatcher = new TextWatcher() {
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
