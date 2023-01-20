package fr.univ.projet_pma;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeButton implements View.OnClickListener {

    private static final String TAG = HomeButton.class.getName();

    private Button _btn;
    private Context _ctx;

    public HomeButton(Context ctx, Button btn) {
        _ctx = ctx;
        _btn = btn;
        _btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ConnectivityManager cm = (ConnectivityManager)_ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        boolean connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
        if (connected) {
            Intent intent = new Intent(_ctx, ConfigScreen.class);
            _ctx.startActivity(intent);
        } else {
            Toast.makeText(_ctx,"No internet connexion!",Toast.LENGTH_SHORT).show();
        }
    }

}
