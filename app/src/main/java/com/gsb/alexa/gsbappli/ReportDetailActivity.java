package com.gsb.alexa.gsbappli;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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
    TextView medicament = null;
    TextView bilan = null;

    HashMap<String, String> rapport;



    @Override
    public void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.report_detail);

        RapportDAO rapportDAO = new RapportDAO(this);

        date = (TextView)findViewById(R.id.det_date_report);
        nom = (TextView)findViewById(R.id.det_name_report);
        motif = (TextView)findViewById(R.id.det_motif);
        medicament = (TextView)findViewById(R.id.det_medicament);
        bilan = (TextView)findViewById(R.id.det_bilan);

        Intent intent = getIntent();

        long idRapport = intent.getLongExtra("id", 0);

        rapport = rapportDAO.getRapportById(idRapport);

        date.setText(rapport.get("date"));
        motif.setText(rapport.get("motif"));
        bilan.setText(rapport.get("bilan"));

    }
}
