package com.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.models.Datum;
import com.moringaschool.farmsmart.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CropListAdapter extends RecyclerView.Adapter<CropListAdapter.CropViewHolder> {
    private List<Datum> mCrops;
    private Context mContext;

    public CropListAdapter(Context context, List<Datum> crops) {
        mContext = context;
        mCrops = crops;
    }

    @NonNull
    @Override
    public CropViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crop_list_item, parent, false);
        CropViewHolder viewHolder = new CropViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CropViewHolder holder, int position) {
        holder.bindCrop(mCrops.get(position));
    }

    @Override
    public int getItemCount() {
        return mCrops.size();
    }

    public class CropViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cropImageView) ImageView mCropImageView;
        @BindView(R.id.plantNameTextView) TextView mplantNameTextView;
        @BindView(R.id.descriptionTextView) TextView mDescriptionTextView;

        private Context mContext;

        public CropViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindCrop(Datum datum){
            mplantNameTextView.setText(datum.getCommonName());
            mDescriptionTextView.setText(datum.getScientificName());
        }
    }
}
