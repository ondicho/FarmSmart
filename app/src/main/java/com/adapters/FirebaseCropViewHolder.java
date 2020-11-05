package com.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.models.Datum;
import com.moringaschool.farmsmart.FarmDetailActivity;
import com.moringaschool.farmsmart.R;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseCropViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;

    public FirebaseCropViewHolder(@NonNull View itemView) {
        super(itemView);
        mView=itemView;
        mContext=itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindCrop(Datum datum){
        ImageView cropImageView=(ImageView) mView.findViewById(R.id.cropImageView);
        TextView nameTextView=(TextView) mView.findViewById(R.id.commonNameTextView);
        TextView descriptionTextView=(TextView) mView.findViewById(R.id.scientificNameTextView);

    nameTextView.setText(datum.getCommonName());
    descriptionTextView.setText(datum.getScientificName());
    }

    @Override
    public void onClick(View v) {
        final ArrayList<Datum> crops=new ArrayList<>();
        DatabaseReference cropReference= FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CROP);
        cropReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:  dataSnapshot.getChildren()){
                    crops.add(snapshot.getValue(Datum.class));
                }
                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, FarmDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("crops", Parcels.wrap(crops));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
