package fr.univ.projet_pma;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.compose.material3.windowsizeclass.WindowSizeClass;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.color.MaterialColors;

import org.json.JSONObject;

import java.util.ArrayList;

public class AbioticResourcesFragment2 extends Fragment {

    private static final String TAG = AbioticResourcesFragment2.class.getName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment2_abiotic_resources, container, false);

        String UsageAdp = new String();
        String ManufacturingAdp = new String();
        String ManufacturingRAMAdp = new String();
        String ManufacturingCPUAdp = new String();
        String ManufacturingSSDAdp = new String();
        String ManufacturingHDDAdp = new String();
        String ManufacturingOtherAdp = new String();

        Intent intent = getActivity().getIntent();

        try {
            Jsonparsing DataJson = new Jsonparsing(new JSONObject(intent.getStringExtra("data")));
            DataJson.RetrieveValueJson("adp");
            UsageAdp = DataJson.getUsage();
            ManufacturingAdp = DataJson.getManufacturing();
            ManufacturingCPUAdp = DataJson.getManufacturingCPU();
            ManufacturingRAMAdp = DataJson.getManufacturingRAM();
            ManufacturingSSDAdp = DataJson.getManufacturingSSD();
            ManufacturingHDDAdp = DataJson.getManufacturingHDD();
            ManufacturingOtherAdp = DataJson.getManufacturingOther();
        } catch (Throwable t){
            Log.e(TAG, "Could not parse malformed JSON");
        }
        Display display1 = getActivity().getWindowManager().getDefaultDisplay();
        int stageWidth = display1.getWidth();
        int stageHeigh = display1.getHeight();

        System.out.println("taille fragment : " + stageWidth);
        System.out.println("taille fragment : " + stageHeigh);

        TextView Total = (TextView) v.findViewById(R.id.TotaltextAdp);
        Total.setText(Float.toString(Float.valueOf(UsageAdp) + Float.valueOf(ManufacturingAdp)));

        PieChart pieChart = v.findViewById(R.id.AbioticPieChart);

        //Putting data inside array for the piechart
        ArrayList<PieEntry> data = new ArrayList<>();
        data.add(new PieEntry(Float.valueOf(UsageAdp), "Utilisation"));
        data.add(new PieEntry(Float.valueOf(ManufacturingAdp), "Fabrication"));
        data.add(new PieEntry(Float.valueOf(ManufacturingRAMAdp), "Fabrication RAM"));
        data.add(new PieEntry(Float.valueOf(ManufacturingCPUAdp), "Fabrication CPU"));
        data.add(new PieEntry(Float.valueOf(ManufacturingSSDAdp), "Fabrication SSD"));
        data.add(new PieEntry(Float.valueOf(ManufacturingHDDAdp), "Fabrication HHD"));
        data.add(new PieEntry(Float.valueOf(ManufacturingOtherAdp), "Fabrication Autres"));

        int[] Colors = {MaterialColors.getColor(v, R.attr.colorPrimary), MaterialColors.getColor(v, R.attr.colorPrimaryContainer), MaterialColors.getColor(v, R.attr.colorSecondary), MaterialColors.getColor(v, R.attr.colorSecondaryContainer), MaterialColors.getColor(v, R.attr.colorTertiary), MaterialColors.getColor(v, R.attr.colorTertiaryContainer), MaterialColors.getColor(v, R.attr.colorOutline)};
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for(int i=0; i <Colors.length ; i++) {
            colors.add(Colors[i]);
        }

        //Set parameters for piechart's inside data
        PieDataSet pieDataSet = new PieDataSet(data,"");
        pieDataSet.setColors(colors);
        pieDataSet.setDrawValues(false);
        PieData pieData = new PieData(pieDataSet);

        //Set parameters for piechart view
        pieChart.setData(pieData);
        pieChart.setDrawEntryLabels(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setHoleRadius(35);
        pieChart.setRotationEnabled(false);

        //Set legend data and parameters
        ArrayList<LegendEntry> EntryLegendData = new ArrayList<>();
        EntryLegendData.add(new LegendEntry((getResources().getString(R.string.resultUtilisation)) + " " + UsageAdp, Legend.LegendForm.CIRCLE, 11f,4f,null, Colors[0]));
        EntryLegendData.add(new LegendEntry((getResources().getString(R.string.resultManufacture)) + " " + ManufacturingAdp, Legend.LegendForm.CIRCLE, 11f,4f,null, Colors[1]));
        EntryLegendData.add(new LegendEntry((getResources().getString(R.string.resultManufactureRAM)) + " " + ManufacturingRAMAdp, Legend.LegendForm.CIRCLE, 11f,4f,null, Colors[2]));
        EntryLegendData.add(new LegendEntry((getResources().getString(R.string.resultManufactureCPU)) + " " + ManufacturingCPUAdp, Legend.LegendForm.CIRCLE, 11f,4f,null, Colors[3]));
        EntryLegendData.add(new LegendEntry((getResources().getString(R.string.resultManufactureSSD)) + " " + ManufacturingSSDAdp, Legend.LegendForm.CIRCLE, 11f,4f,null, Colors[4]));
        EntryLegendData.add(new LegendEntry((getResources().getString(R.string.resultManufactureHDD)) + " " + ManufacturingHDDAdp, Legend.LegendForm.CIRCLE, 11f,4f,null, Colors[5]));
        EntryLegendData.add(new LegendEntry((getResources().getString(R.string.resultManufactureOther)) + " " + ManufacturingOtherAdp, Legend.LegendForm.CIRCLE, 11f,4f,null, Colors[6]));

        Legend legend = pieChart.getLegend();
        legend.setCustom(EntryLegendData);
        legend.setEnabled(true);
        legend.setWordWrapEnabled(true);
        legend.setXEntrySpace(14f);
        legend.setYEntrySpace(4f);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setTextColor(MaterialColors.getColor(v, R.attr.colorOnBackground));

        return v;
    }
}
