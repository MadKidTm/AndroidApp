package com.gsb.alexa.gsbappli;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

    HashMap<String, String> rapport;
    HashMap<String, String> offre;
    HashMap<String, String> medicament;
    HashMap<String, String> medecin;



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

        nom.setText(medecin.get("nom")+" "+medecin.get("prenom"));
        date.setText(rapport.get("date"));
        motif.setText(rapport.get("motif"));
        bilan.setText(rapport.get("bilan"));
        medicamentText.setText(medicament.get("nom")+" x "+offre.get("quantite"));

    }
}
