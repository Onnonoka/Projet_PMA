package fr.univ.projet_pma;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ImpactVisualisationScreen extends AppCompatActivity {

    private static final String TAG = ImpactVisualisationScreen.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.impact_visualisation_screen);

        ArrayList<Integer> elem = new ArrayList<>();
        elem.add(10);
        elem.add(20);
        elem.add(12);

        PieChart pieChart = findViewById(R.id.graphLayout);
        pieChart.addElement(elem);
    }
}