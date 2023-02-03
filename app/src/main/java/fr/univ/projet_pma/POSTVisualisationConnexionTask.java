package fr.univ.projet_pma;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.Callable;

public class POSTVisualisationConnexionTask extends URLConnexionTask implements Callable<JSONObject> {

    private static final String TAG = POSTVisualisationConnexionTask.class.getName();

    public POSTVisualisationConnexionTask() {
        super("/server/?verbose=true&allocation=TOTAL");
    }

    @Override
    public JSONObject call() {
        try {
            openConnexion("POST");

            JSONObject jsonMessage = buildJSON();
            assert jsonMessage != null;
            Log.i(TAG, jsonMessage.toString());
            writeOutputStream(jsonMessage.toString());

            String response = getInputStreamString();

            Log.i(TAG, response);
            JSONObject jsonResponse = new JSONObject(response);

            return jsonResponse;
        } catch (Exception e) {
            return null;
        }

    }

    private JSONObject buildJSON() {
        try {
            JSONObject jsonObject = new JSONObject();
                JSONObject modelJsonObject = new JSONObject();
                JSONObject configJsonObject = new JSONObject();
                    JSONObject cpuJsonObject = new JSONObject();
                    JSONArray ramJsonArray = new JSONArray();
                        JSONObject ramJsonObject = new JSONObject();
                    JSONArray diskJsonArray = new JSONArray();
                        JSONObject ssdJsonObject = new JSONObject();
                    JSONObject powerUnitJsonObject = new JSONObject();
                JSONObject usageJsonObject = new JSONObject();
                    JSONArray timeWorkloadJsonArray = new JSONArray();

            modelJsonObject.put("type", Configuration.getInstance().get_serverType());
            jsonObject.put("model", modelJsonObject);

            cpuJsonObject.put("tdp", Configuration.getInstance().get_tdp());
            cpuJsonObject.put("core_units", Configuration.getInstance().get_coreUnits());
            cpuJsonObject.put("units", Configuration.getInstance().get_quantityCPU());
            cpuJsonObject.put("family", Configuration.getInstance().get_architecture());
            configJsonObject.put("cpu", cpuJsonObject);

            ramJsonObject.put("units", Configuration.getInstance().get_quantityRAM());
            ramJsonObject.put("capacity", Configuration.getInstance().get_capacityRAM());
            ramJsonObject.put("manufacturer", Configuration.getInstance().get_manufacturerRAM());
            ramJsonArray.put(ramJsonObject);
            configJsonObject.put("ram", ramJsonArray);

            ssdJsonObject.put("type", "ssd");
            ssdJsonObject.put("units", Configuration.getInstance().get_quantitySSD());
            ssdJsonObject.put("capacity", Configuration.getInstance().get_capacitySSD());
            ssdJsonObject.put("manufacturer", Configuration.getInstance().get_manufacturerSSD());
            diskJsonArray.put(ssdJsonObject);

            if (Configuration.getInstance().get_quantityHDD() != -1) {
                JSONObject hddJsonObject = new JSONObject();
                hddJsonObject.put("type", "hdd");
                hddJsonObject.put("units", Configuration.getInstance().get_quantityHDD());
                diskJsonArray.put(hddJsonObject);
            }
            configJsonObject.put("disk", diskJsonArray);

            powerUnitJsonObject.put("units", Configuration.getInstance().get_quantityPSU());
            configJsonObject.put("power_supply", powerUnitJsonObject);
            jsonObject.put("configuration", configJsonObject);

            usageJsonObject.put("years_use_time", Configuration.getInstance().get_lifespan());
            String locationCode = Configuration.getInstance().get_APIResources().get_countryMap().get(
                    Configuration.getInstance().get_localisation()
            );
            usageJsonObject.put("usage_location", locationCode);
            if (Configuration.getInstance().get_methode().equals(Configuration.getInstance().get_APIResources().get_methodeList().get(0))) {
                usageJsonObject.put("hours_electrical_consumption", Configuration.getInstance().get_avgConsumption());
            } else {
                usageJsonObject.put("hours_electrical_consumption", JSONObject.NULL);
                JSONObject slot1 = new JSONObject();
                JSONObject slot2 = new JSONObject();
                JSONObject slot3 = new JSONObject();
                slot1.put("time_percentage",  Configuration.getInstance().get_1Time());
                slot1.put("load_percentage",  Configuration.getInstance().get_1Load());
                slot2.put("time_percentage",  Configuration.getInstance().get_2Time());
                slot2.put("load_percentage",  Configuration.getInstance().get_2Load());
                slot3.put("time_percentage",  Configuration.getInstance().get_3Time());
                slot3.put("load_percentage",  Configuration.getInstance().get_3Load());
                timeWorkloadJsonArray.put(slot1);
                timeWorkloadJsonArray.put(slot2);
                timeWorkloadJsonArray.put(slot3);
            }
            usageJsonObject.put("time_workload", timeWorkloadJsonArray);
            jsonObject.put("usage", usageJsonObject);
            Log.i(TAG, jsonObject.toString());
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
