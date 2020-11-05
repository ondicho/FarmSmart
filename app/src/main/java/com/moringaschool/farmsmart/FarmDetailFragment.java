package com.moringaschool.farmsmart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.Constants;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.farmsmart.R;
import com.models.Datum;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FarmDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FarmDetailFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.searchedCropimageView) ImageView mImageLabel;
    @BindView(R.id.commonNameTextView) TextView mCommonNameLabel;
    @BindView(R.id.scientificNameTextView) TextView mScientificNameLabel;
    @BindView(R.id.saveSearchedCropButton) Button mSaveSearchedCropButton;

    private Datum mCrop;

    public FarmDetailFragment() {
        // Required empty public constructor
    }

    public static FarmDetailFragment newInstance(Datum crop) {
        FarmDetailFragment farmDetailFragment = new FarmDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("crop",Parcels.wrap(crop));
        farmDetailFragment.setArguments(args);
        return farmDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       mCrop= Parcels.unwrap(getArguments().getParcelable("crop"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_farm_detail,container,false);
        ButterKnife.bind(this,view);
        Picasso.get().load(mCrop.getImageUrl()).into(mImageLabel);
        mCommonNameLabel.setText(mCrop.getCommonName());
        mScientificNameLabel.setText(mCrop.getScientificName());

        mSaveSearchedCropButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveSearchedCropButton) {
            DatabaseReference cropReference = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_CROP);
            cropReference.push().setValue(mCrop);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
