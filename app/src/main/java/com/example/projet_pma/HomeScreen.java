package com.example.projet_pma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {

    private HomeButton _homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        Button btn = findViewById(R.id.homeButton);
        _homeButton = new HomeButton(this, btn);
    }

}