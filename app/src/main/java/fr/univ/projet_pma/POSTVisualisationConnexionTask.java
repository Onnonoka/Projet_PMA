package fr.univ.projet_pma;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.Callable;

public class POSTVisualisationConnexionTask extends URLConnexionTask implements Callable<JSONObject> {

    private static final String TAG = POSTVisualisationConnexionTask.class.getName();

    public POSTVisualisationConnexionTask() {
        super("/server/?verbose=false");
    }

    @Override
    public JSONObject call() throws Exception {
        openConnexion("POST");

        JSONObject jsonMessage = buildJSON();
        assert jsonMessage != null;
        Log.i(TAG, jsonMessage.toString());
        writeOutputStream(jsonMessage.toString());

        String response = getInputStreamString();

        Log.i(TAG, response);
        JSONObject jsonResponse = new JSONObject(response);

        return jsonResponse;
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
            usageJsonObject.put("hours_electrical_consumption", Configuration.getInstance().get_avgConsumption());
            jsonObject.put("usage", usageJsonObject);

            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
