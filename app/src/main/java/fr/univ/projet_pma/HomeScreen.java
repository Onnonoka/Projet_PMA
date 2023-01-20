package fr.univ.projet_pma;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomeScreen extends AppCompatActivity {
    private static final String TAG = HomeScreen.class.getName();

    private HomeButton _homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        Button btn = findViewById(R.id.homeButton);
        _homeButton = new HomeButton(this, btn);
    }

}