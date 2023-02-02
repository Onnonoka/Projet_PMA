package fr.univ.projet_pma;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.material.color.MaterialColors;

import org.json.JSONObject;

import java.util.ArrayList;

public class GreenHouseGasFragment2 extends Fragment {

    private static final String TAG = GreenHouseGasFragment2.class.getName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment2_greenhouse_gas, container, false);

        String UsageGwp = new String();
        String ManufacturingGwp = new String();
        String ManufacturingRAMGwp = new String();
        String ManufacturingCPUGwp = new String();
        String ManufacturingSSDGwp = new String();
        String ManufacturingHDDGwp = new String();
        String ManufacturingOtherGwp = new String();

        Intent intent = getActivity().getIntent();

        try {
            Jsonparsing DataJsonGwp = new Jsonparsing(new JSONObject(intent.getStringExtra("data")));
            DataJsonGwp.RetrieveValueJson("gwp");
            UsageGwp = DataJsonGwp.getUsage();
            ManufacturingGwp = DataJsonGwp.getManufacturing();
            ManufacturingCPUGwp = DataJsonGwp.getManufacturingCPU();
            ManufacturingRAMGwp = DataJsonGwp.getManufacturingRAM();
            ManufacturingSSDGwp = DataJsonGwp.getManufacturingSSD();
            ManufacturingHDDGwp = DataJsonGwp.getManufacturingHDD();
            ManufacturingOtherGwp = DataJsonGwp.getManufacturingOther();
        } catch (Throwable t){
            Log.e(TAG, "Could not parse malformed JSON");
        }


        TextView Total = (TextView) v.findViewById(R.id.TotaltextGwp);
        Total.setText(Float.toString(Float.valueOf(UsageGwp) + Float.valueOf(ManufacturingGwp)));

        PieChart pieChart = v.findViewById(R.id.GwpPieChart);

        //Putting data inside array for the piechart
        ArrayList<PieEntry> data = new ArrayList<>();
        data.add(new PieEntry(Float.valueOf(UsageGwp), "Utilisation"));
        data.add(new PieEntry(Float.valueOf(ManufacturingGwp), "Fabrication"));
        data.add(new PieEntry(Float.valueOf(ManufacturingRAMGwp), "Fabrication RAM"));
        data.add(new PieEntry(Float.valueOf(ManufacturingCPUGwp), "Fabrication CPU"));
        data.add(new PieEntry(Float.valueOf(ManufacturingSSDGwp), "Fabrication SSD"));
        data.add(new PieEntry(Float.valueOf(ManufacturingHDDGwp), "Fabrication HHD"));
        data.add(new PieEntry(Float.valueOf(ManufacturingOtherGwp), "Fabrication Autres"));

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
        EntryLegendData.add(new LegendEntry((getResources().getString(R.string.resultUtilisation)) + " " + UsageGwp, Legend.LegendForm.CIRCLE, 11f,4f,null, Colors[0]));
        EntryLegendData.add(new LegendEntry((getResources().getString(R.string.resultManufacture)) + " " + ManufacturingGwp, Legend.LegendForm.CIRCLE, 11f,4f,null, Colors[1]));
        EntryLegendData.add(new LegendEntry((getResources().getString(R.string.resultManufactureRAM)) + " " + ManufacturingRAMGwp, Legend.LegendForm.CIRCLE, 11f,4f,null, Colors[2]));
        EntryLegendData.add(new LegendEntry((getResources().getString(R.string.resultManufactureCPU)) + " " + ManufacturingCPUGwp, Legend.LegendForm.CIRCLE, 11f,4f,null, Colors[3]));
        EntryLegendData.add(new LegendEntry((getResources().getString(R.string.resultManufactureSSD)) + " " + ManufacturingSSDGwp, Legend.LegendForm.CIRCLE, 11f,4f,null, Colors[4]));
        EntryLegendData.add(new LegendEntry((getResources().getString(R.string.resultManufactureHDD)) + " " + ManufacturingHDDGwp, Legend.LegendForm.CIRCLE, 11f,4f,null, Colors[5]));
        EntryLegendData.add(new LegendEntry((getResources().getString(R.string.resultManufactureOther)) + " " + ManufacturingOtherGwp, Legend.LegendForm.CIRCLE, 11f,4f,null, Colors[6]));

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
