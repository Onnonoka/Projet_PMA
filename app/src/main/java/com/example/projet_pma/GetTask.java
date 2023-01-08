package com.example.projet_pma;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetTask {

    private static final String TAG = GetTask.class.getName();

    private static final String BASE_URL = "https://uppa.api.boavizta.org/v1/utils";
    private String _endpoint = "";

    private HttpURLConnection _urlConnection;

    public GetTask(String endpoint) {
        _endpoint = endpoint;
    }

    protected void openConnexion() throws Exception {
        URL url = new URL(BASE_URL + _endpoint);
        _urlConnection = (HttpURLConnection) url.openConnection();
        _urlConnection.setRequestMethod("GET");
    }

    protected String getInputStreamString() {
        try {
            InputStream in = new BufferedInputStream(_urlConnection.getInputStream());
            BufferedReader r = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                sb.append(line);
            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (_urlConnection != null) {
                _urlConnection.disconnect();
            }
        }
        return "";
    }

}
