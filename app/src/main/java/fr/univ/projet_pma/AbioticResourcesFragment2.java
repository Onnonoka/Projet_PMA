package fr.univ.projet_pma;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class AbioticResourcesFragment2 extends Fragment {

    private static final String TAG = AbioticResourcesFragment2.class.getName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment2_abiotic_resources, container, false);

        PieChart pieChart = v.findViewById(R.id.AbioticPieChart);

        ArrayList<PieEntry> data = new ArrayList<>();
        data.add(new PieEntry(30, "Utilisation"));
        data.add(new PieEntry(400, "Fabrication"));
        data.add(new PieEntry(200, "Fabrication RAM"));
        data.add(new PieEntry(100, "Fabrication CPU"));
        data.add(new PieEntry(230, "Fabrication SSD"));
        data.add(new PieEntry(450, "Fabrication HHD"));
        data.add(new PieEntry(170, "Fabrication Autres"));

        int[] Colors = {Color.rgb(192,0,0), Color.rgb(255,0,0), Color.rgb(255,192,0),Color.rgb(127,127,127), Color.rgb(146,208,80), Color.rgb(0,176,80), Color.rgb(79,129,189)};
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for(int i=0; i <Colors.length ; i++) {
            colors.add(Colors[i]);
        }

        PieDataSet pieDataSet = new PieDataSet(data,"");
        pieDataSet.setColors(colors);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(10f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.setDrawEntryLabels(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.animate();
        pieChart.getLegend().setWordWrapEnabled(true);

        Legend legend = pieChart.getLegend();
        legend.setEnabled(true);
        legend.setDrawInside(false);

        return v;
    }
}
