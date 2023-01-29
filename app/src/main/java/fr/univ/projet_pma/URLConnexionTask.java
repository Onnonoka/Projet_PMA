package fr.univ.projet_pma;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class URLConnexionTask {

    private static final String TAG = URLConnexionTask.class.getName();

    private static final String BASE_URL = "https://uppa.api.boavizta.org/v1";
    private String _endpoint = "";

    private HttpURLConnection _urlConnection;

    public URLConnexionTask(String endpoint) {
        _endpoint = endpoint;
    }

    protected void openConnexion(String method) throws Exception {
        URL url = new URL(BASE_URL + _endpoint);
        _urlConnection = (HttpURLConnection) url.openConnection();
        _urlConnection.setRequestMethod(method);
        if (method.equals("POST")) {
            _urlConnection.setDoOutput(true);
            _urlConnection.setConnectTimeout(1500);
            _urlConnection.setRequestProperty("Content-Type", "application/json");
            _urlConnection.connect();
        }
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

    protected void writeOutputStream(String query) {
        try {
            byte[] outputBytes = query.getBytes(StandardCharsets.UTF_8);
            OutputStream os = _urlConnection.getOutputStream();
            os.write(outputBytes);
            os.flush();
            os.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
