package com.example.projet_pma;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class AnalyzeButton implements View.OnClickListener {

    private static final String TAG = AnalyzeButton.class.getName();

    private Button _btn;
    private Context _ctx;

    public AnalyzeButton(Context ctx, Button btn) {
        _btn = btn;
        _ctx = ctx;

        _btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(_ctx,"TEST!",Toast.LENGTH_SHORT).show();
        TaskRunner taskRunner = new TaskRunner();

    }


}