package com.gsb.alexa.gsbappli;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gsb.alexa.gsbappli.Classes.MedecinDAO;
import com.gsb.alexa.gsbappli.Classes.MedicamentDAO;
import com.gsb.alexa.gsbappli.Classes.OffrirDAO;
import com.gsb.alexa.gsbappli.Classes.RapportDAO;

import java.util.HashMap;

/**
 * Created by Alex on 11/04/2018.
 */

public class ReportDetailActivity extends Activity
{
    TextView date = null;
    TextView nom = null;
    TextView motif = null;
    TextView medicamentText = null;
    TextView bilan = null;
    ImageButton delete = null;

    HashMap<String, String> rapport;
    HashMap<String, String> offre;
    HashMap<String, String> medicament;
    HashMap<String, String> medecin;

    Context context = this;


    @Override
    public void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.report_detail);

        RapportDAO rapportDAO = new RapportDAO(this);
        OffrirDAO offrirDAO = new OffrirDAO(this);
        MedicamentDAO medicamenDAO = new MedicamentDAO (this);
        MedecinDAO medecinDAO = new MedecinDAO(this);

        date = (TextView)findViewById(R.id.det_date_report);
        nom = (TextView)findViewById(R.id.det_name_report);
        motif = (TextView)findViewById(R.id.det_motif);
        medicamentText = (TextView)findViewById(R.id.det_medicament);
        bilan = (TextView)findViewById(R.id.det_bilan);


        Intent intent = getIntent();

        long idRapport = intent.getLongExtra("id", 0);

        rapport = rapportDAO.getRapportById(idRapport);
        offre = offrirDAO.getOffreByIdRapport(idRapport);
        medecin = medecinDAO.getMedecinById(Long.valueOf(rapport.get("idMedecin")));
        medicament = medicamenDAO.getMedicamentById(Long.valueOf(offre.get("idMedicament")));


        // Gestion de la date
        String dateRapport = rapport.get("date");

        String year = dateRapport.substring(0, dateRapport.indexOf('-'));
        String month = dateRapport.substring(dateRapport.indexOf('-')+1, dateRapport.lastIndexOf("-"));
        String day = dateRapport.substring(dateRapport.lastIndexOf('-')+1);


        String nomText = "chez "+medecin.get("nom")+" "+medecin.get("prenom");
        String dateText = "Rapport du "+day+" "+getMonthName(Integer.valueOf(month))+" "+year;
        String medicText = medicament.get("nom")+" x "+offre.get("quantite");

        nom.setText(nomText);
        date.setText(dateText);
        motif.setText(rapport.get("motif"));
        bilan.setText(rapport.get("bilan"));
        medicamentText.setText(medicText);
    }

    public void onRestart(){
        super.onRestart();

        delete = (ImageButton)findViewById(R.id.deleteRapport);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RapportDAO rapportDAO = new RapportDAO(context);

                rapportDAO.deleteById(Long.valueOf(rapport.get("id")));

                Intent intent = new Intent(ReportDetailActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

    }

    private String getMonthName(int numMonth){
        String[] month = new String[] {"Janvier","Fervrier","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Decembre"};

        return month[numMonth - 1];
    }
}
