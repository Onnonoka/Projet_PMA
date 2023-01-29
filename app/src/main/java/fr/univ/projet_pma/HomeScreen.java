package fr.univ.projet_pma;


import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomeScreen extends AppCompatActivity {
    private static final String TAG = HomeScreen.class.getName();

    private HomeButton _homeButton;

    private NetworkChangeReceiver _receiver;
    private IntentFilter _intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        Button btn = findViewById(R.id.homeButton);
        _homeButton = new HomeButton(this, btn);

        _receiver = new NetworkChangeReceiver();
        _intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(_receiver, _intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(_receiver);
    }
}