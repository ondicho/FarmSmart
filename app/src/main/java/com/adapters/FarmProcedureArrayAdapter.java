package com.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

public class FarmProcedureArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mCrops;
    private String[] mDescription;

    public FarmProcedureArrayAdapter(Context mContext, int resource, String[] mCrops, String[] mDescription){
        super(mContext,resource);
        this.mContext=mContext;
        this.mCrops=mCrops;
        this.mDescription=mDescription;
    }

    @Override
    public Object getItem(int position) {
        String crop = mCrops[position];
        String description = mDescription[position];
        return String.format("%s \nServes great: %s", crop, description);
    }

    @Override
    public int getCount() {
        return mCrops.length;
    }
}

