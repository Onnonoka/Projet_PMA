package com.example.projet_pma;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ConfigViewPagerAdapter extends FragmentStateAdapter {


    public ConfigViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new CustomFragment(R.layout.fragment_cpu_config);
            case 1:
                return new CustomFragment(R.layout.fragment_ram_config);
            case 2:
                return new CustomFragment(R.layout.fragment_ssd_config);
            case 3:
                return new CustomFragment(R.layout.fragment_other_config);
            case 4:
                return new CustomFragment(R.layout.fragment_usage_config);
            default:
                return new CustomFragment(R.layout.fragment_cpu_config);
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
