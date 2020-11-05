package com.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.models.Datum;
import com.moringaschool.farmsmart.FarmDetailActivity;
import com.moringaschool.farmsmart.R;

import org.parceler.Parcels;

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

    public class CropViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.cropImageView) ImageView mCropImageView;
        @BindView(R.id.plantNameTextView) TextView mplantNameTextView;
        @BindView(R.id.descriptionTextView) TextView mDescriptionTextView;

        private Context mContext;

        public CropViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindCrop(Datum datum){
            mplantNameTextView.setText(datum.getCommonName());
            mDescriptionTextView.setText(datum.getScientificName());
        }

        @Override
        public void onClick(View v) {
            int itemPosition=getLayoutPosition();
            Intent intent=new Intent(mContext, FarmDetailActivity.class);
            intent.putExtra("position",itemPosition);
            intent.putExtra("crops", Parcels.wrap(mCrops));
            mContext.startActivity(intent);
        }
    }
}
