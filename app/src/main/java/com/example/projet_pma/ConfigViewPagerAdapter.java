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
                return new CPUConfigFragment();
            case 1:
                return new RAMConfigFragment();
            case 2:
                return new SSDConfigFragment();
            case 3:
                return new OtherConfigFragment();
            case 4:
                return new UsageConfigFragment();
            default:
                return new CPUConfigFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
