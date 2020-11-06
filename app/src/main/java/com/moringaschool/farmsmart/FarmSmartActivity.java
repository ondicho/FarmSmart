package com.moringaschool.farmsmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FarmSmartActivity extends AppCompatActivity  implements View.OnClickListener {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    @BindView(R.id.findCropButton) Button mFindCropButton;
    @BindView(R.id.savedCropsbutton) Button mSavedCropsButton;
//    @BindView(R.id.appNameTextView) TextView mAppNameTextView;

//    private DatabaseReference mSearchedCropReference;
//    private static final String TAG = "FarmSmartActivity";
//    private ValueEventListener  mSearchedCropReferenceListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//
////        mSearchedCropReference = FirebaseDatabase
////                .getInstance()
////                .getReference()
////                .child(Constants.FIREBASE_CHILD_SEARCHED_CROP);
////
////        mSearchedCropReferenceListener=mSearchedCropReference.addValueEventListener(new ValueEventListener() {
//
//
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                for(DataSnapshot userInputSnapshot: snapshot.getChildren()){
//                    String userInput=userInputSnapshot.getValue().toString();
//                    Log.d("Searched crops updated","Searched Crop:" + userInput);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//
//            }
//        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_smart);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        mSharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
        mEditor=mSharedPreferences.edit();

        mFindCropButton.setOnClickListener(this);
        mSavedCropsButton.setOnClickListener(this);


    }
        @Override
        public void onClick(View v) {
            if(v == mFindCropButton) {
              Intent intent=new Intent(FarmSmartActivity.this,FarmListActivity.class);
              startActivity(intent);
            }

            if(v == mSavedCropsButton) {
                Intent intent = new Intent(FarmSmartActivity.this, SavedCropListActivity.class);
                startActivity(intent);
            }

            }
        }



//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mSearchedCropReference.removeEventListener(mSearchedCropReferenceListener);
//    }
//
//    private void addToSharedPreferences(String location) {
//        mEditor.putString(Constants.PREFERENCES_CROP_KEY, location).apply();
//    }
//}