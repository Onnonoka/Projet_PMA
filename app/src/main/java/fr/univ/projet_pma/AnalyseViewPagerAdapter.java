package fr.univ.projet_pma;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AnalyseViewPagerAdapter extends FragmentStateAdapter {

    private static final String TAG = AnalyseViewPagerAdapter.class.getName();

    public AnalyseViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new GreenHouseGasFragment2();
            case 1:
                return new PrimaryEnergieFragment2();
            case 2:
                return new AbioticResourcesFragment2();
            default:
                return new GreenHouseGasFragment2();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}