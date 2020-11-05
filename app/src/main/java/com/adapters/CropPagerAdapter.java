package com.adapters;




import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.models.Datum;
import com.moringaschool.farmsmart.FarmDetailFragment;

import java.util.List;

public class CropPagerAdapter extends FragmentPagerAdapter {
    private List<Datum> mCrops;

    public CropPagerAdapter(FragmentManager fm,int behavior, List<Datum> crops){
        super(fm,behavior);
        mCrops=crops;
    }
    @Override
    public Fragment getItem(int position) {
        return FarmDetailFragment.newInstance(mCrops.get(position));
    }

    @Override
    public int getCount() {
        return mCrops.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mCrops.get(position).getCommonName();
    }
}
