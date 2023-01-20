package fr.univ.projet_pma;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class GETResourcesConnexionTask extends URLConnexionTask implements Callable<List<String>> {

    private static final String TAG = GETResourcesConnexionTask.class.getName();

    public GETResourcesConnexionTask(String endpoint) {
        super(endpoint);
    }

    @Override
    public List<String> call() throws Exception {
        openConnexion("GET");

        String inputStreamString = getInputStreamString();
        Log.i(TAG, inputStreamString);

        JSONArray jsonArray = new JSONArray(inputStreamString);

        return JSONArrayToStringArray(jsonArray);
    }

    private ArrayList<String> JSONArrayToStringArray(JSONArray jsonArray) throws JSONException {
        ArrayList<String> data = new ArrayList<String>();
        for (int i = 0; i < jsonArray.length(); ++i) {
            data.add(jsonArray.getString(i));
        }
        return data;
    }
}
