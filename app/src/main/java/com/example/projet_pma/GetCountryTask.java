package com.example.projet_pma;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;

public class GetCountryTask extends GetTask implements Callable<Map<String, String>> {

    private static final String TAG = GetCountryTask.class.getName();

    public GetCountryTask() {
        super("/country_code");
    }

    @Override
    public Map<String, String> call() throws Exception {
        openConnexion();

        String inputStreamString = getInputStreamString();
        Log.i(TAG, inputStreamString);

        JSONObject jsonObject = new JSONObject(inputStreamString);

        return JSONObjectToHashMap(jsonObject);
    }

    public static Map<String, String> JSONObjectToHashMap(JSONObject jsonObject)  throws JSONException {
        Map<String, String> map = new HashMap<String, String>();
        Iterator<String> keys = jsonObject.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            String value = jsonObject.getString(key);

            map.put(key, value);
        }
        return map;
    }

}
