package fr.univ.projet_pma;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONObject;


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
        TaskRunner taskRunner = new TaskRunner();
        taskRunner.executeAsync(new POSTVisualisationConnexionTask(), new TaskRunner.Callback<JSONObject>() {
            @Override
            public void onComplete(JSONObject result) {
                // Do screen change
                if (result == null) {
                    Toast.makeText(_ctx,"An error has occurred!",Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(_ctx, AnalyseScreen.class);
                    intent.putExtra("data", result.toString());
                    _ctx.startActivity(intent);
                    Log.i(TAG, "TASK Complete");
                }
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(_ctx,"An error has occurred!",Toast.LENGTH_SHORT).show();
            }

        });
    }


}
