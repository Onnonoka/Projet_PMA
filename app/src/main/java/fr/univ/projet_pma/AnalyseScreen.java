package fr.univ.projet_pma;


import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class AnalyseScreen extends AppCompatActivity {

    private static final String TAG = AnalyseScreen.class.getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analyse_screen);

        ViewPager2 viewPager2 = findViewById(R.id.configViewer2);
        TabLayout tabLayout = findViewById(R.id.tabLayout2);

        AnalyseViewPagerAdapter adapter = new AnalyseViewPagerAdapter(this);
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
