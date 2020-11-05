package com.moringaschool.farmsmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Constants;
import com.adapters.FirebaseCropViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.models.Datum;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedCropListActivity extends AppCompatActivity {

    private DatabaseReference mCropReference;
    private FirebaseRecyclerAdapter<Datum, FirebaseCropViewHolder> mFirebaseAdapter;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_list);
        ButterKnife.bind(this);

        mCropReference= FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CROP);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        FirebaseRecyclerOptions<Datum> options =
                new FirebaseRecyclerOptions.Builder<Datum>()
                        .setQuery(mCropReference, Datum.class)
                        .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Datum, FirebaseCropViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseCropViewHolder firebaseCropViewHolder, int i, @NonNull Datum datum) {
                firebaseCropViewHolder.bindCrop(datum);
            }

            @NonNull
            @Override
            public FirebaseCropViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.crop_list_item,parent,false);
                return new FirebaseCropViewHolder(view);
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }
}
