package fr.univ.projet_pma;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ConfigScreen extends AppCompatActivity {

    private static final String TAG = ConfigScreen.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_screen);

        ViewPager2 viewPager2 = findViewById(R.id.configViewer);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        Button btn = findViewById(R.id.analyzeButton);
        AnalyzeButton analyzeButton = new AnalyzeButton(this, btn);

        ConfigViewPagerAdapter adapter = new ConfigViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        // need empty fonction for doing nothing special
                    }
                }).attach();
    }
}