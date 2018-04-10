package com.gsb.alexa.gsbappli;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gsb.alexa.gsbappli.Classes.MedecinDAO;
import com.gsb.alexa.gsbappli.Classes.MedicamentDAO;
import com.gsb.alexa.gsbappli.Classes.Offrir;
import com.gsb.alexa.gsbappli.Classes.OffrirDAO;
import com.gsb.alexa.gsbappli.Classes.Rapport;
import com.gsb.alexa.gsbappli.Classes.RapportDAO;
import com.gsb.alexa.gsbappli.Classes.Visiteur;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Alexa on 24/03/2018.
 */

public class AddRepportActivity extends Activity {

    private TextView textForm = null;
    private Spinner medecin = null;
    private Spinner medicament = null;
    private EditText quantite = null;
    private DatePicker date = null;
    private EditText motif = null;
    private EditText bilan = null;

    private Button send = null;

    private Context context = this;
    private Visiteur visiteur;

    private ArrayList<String> medecins = new ArrayList<String>();
    private ArrayList<String> medicaments = new ArrayList<String>();


    public void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        this.setContentView(R.layout.gsb_report_management);

        MedecinDAO medecinDAO = new MedecinDAO(this);
        final MedicamentDAO medicamentDAO = new MedicamentDAO(this);

        System.out.println("Nombre de medecins : "+ medecinDAO.getNumberOfLines());

        textForm = (TextView)findViewById(R.id.rap_textForm);
        medecin = (Spinner)findViewById(R.id.rap_medecin);
        medicament = (Spinner)findViewById(R.id.rap_medicament);
        quantite = (EditText)findViewById(R.id.rap_quantite);
        date = (DatePicker)findViewById(R.id.rap_date);
        motif = (EditText)findViewById(R.id.rap_motif);
        bilan = (EditText)findViewById(R.id.rap_bilan);
        send = (Button)findViewById(R.id.rap_send);

        Intent i = getIntent();
        visiteur = i.getParcelableExtra("com.gsb.alexa.gsbappli.Classes.Visiteur");

        /*Date aujourdhui = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/YYYY ");
        String now = formater.format(aujourdhui);*/

        //date.setText(now);

        medecinDAO.getMedecins(medecins);
        medicamentDAO.getMedicaments(medicaments);


        //Cette partie sers a peupler mon Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, medecins);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medecin.setAdapter(adapter);

        adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, medicaments);
        medicament.setAdapter(adapter);

        //date.addTextChangedListener(textWatcher);





        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean formReady = true;
                String textDate;

                MedecinDAO medecinDAO = new MedecinDAO(context);
                RapportDAO rapportDAO = new RapportDAO(context);
                OffrirDAO offrirDAO = new OffrirDAO(context);
                Offrir offrir = new Offrir();

                rapportDAO.selectLignes();
                offrirDAO.selectLignes();


                Rapport rapport = new Rapport();

                String identiteMedecin = medecin.getSelectedItem().toString();

                if(identiteMedecin.length() == 0){
                    formReady = false;

                } else {

                    String nomMedecin =  identiteMedecin.substring(0, identiteMedecin.indexOf(' '));
                    String prenomMedecin = identiteMedecin.substring(identiteMedecin.indexOf(' ')+1);

                    rapport.setIdMedecin(medecinDAO.getIdMedecin(nomMedecin, prenomMedecin));
                    rapport.setIdVisiteur(visiteur.getId());
                }

                /*Partie qui gère la date
                textDate = date.getText().toString();
                if(textDate.length() != 0){
                    String jour = textDate.substring(0,2);
                    String mois = textDate.substring(3,5);
                    String annee = textDate.substring(6);



                    if( jour.length() == 2 && mois.length() == 2 && annee.length() == 4 ){

                    date.setHint("JJ/MM/YYYY");
                    date.setHintTextColor(getResources().getColor(R.color.errorColor, null));

                    rapport.setDate(annee+"-"+mois+"-"+jour);
                } else {
                    date.setText("");
                }
            }*/
                String jour = String.valueOf(date.getDayOfMonth());
                String mois = String.valueOf(date.getMonth());
                String annee = String.valueOf(date.getYear());

                rapport.setDate(annee+"-"+mois+"-"+jour);

                if(motif.getText().toString().length() == 0){
                    motif.setHintTextColor(getResources().getColor(R.color.errorColor, null));
                    formReady = false;
                }else{
                    rapport.setMotif(motif.getText().toString());
                }

                if(bilan.getText().toString().length() == 0){
                    bilan.setHintTextColor(getResources().getColor(R.color.errorColor, null));
                    formReady = false;
                } else {
                    rapport.setBilan(bilan.getText().toString());
                }
                if(quantite.getText().toString().length() == 0){
                    quantite.setHintTextColor(getResources().getColor(R.color.errorColor, null));
                }else {
                    offrir.setQuantite(quantite.getText().toString());
                }





                if(formReady){

                    rapportDAO.insertLigne(rapport.getDate(), rapport.getMotif(), rapport.getBilan(), rapport.getIdMedecin(), rapport.getIdVisiteur());

                    offrir.setIdMedicament(medicamentDAO.getMedicamentId(medicament.getSelectedItem().toString()));
                    System.out.println("idMedicament = "+medicamentDAO.getMedicamentId(medicament.getSelectedItem().toString()));

                    offrir.setIdRapport(rapportDAO.getRapportId(rapport.getDate(), rapport.getBilan()));
                    System.out.println("idRapport = "+ rapportDAO.getRapportId(rapport.getDate(), rapport.getBilan()));

                    //offrirDAO.insertLigne(offrir.getIdRapport(), offrir.getIdMedicament(), offrir.getQuantite());



                } else {
                    Toast.makeText(context, "Veuillez remplir tous les champs obligatoires", Toast.LENGTH_LONG).show();
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
