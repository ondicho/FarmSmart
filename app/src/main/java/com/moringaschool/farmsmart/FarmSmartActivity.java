package com.moringaschool.farmsmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FarmSmartActivity extends AppCompatActivity  implements View.OnClickListener {

    @BindView(R.id.cropEditText) EditText mCropEditText;
    @BindView(R.id.editCropButton) Button mEditCropButton;
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;

    private DatabaseReference mSearchedCropReference;
    private static final String TAG = "FarmSmartActivity";
    private ValueEventListener  mSearchedCropReferenceListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedCropReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_CROP);

        mSearchedCropReferenceListener=mSearchedCropReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(DataSnapshot userInputSnapshot: snapshot.getChildren()){
                    String userInput=userInputSnapshot.getValue().toString();
                    Log.d("Searched crops updated","Searched Crop:" + userInput);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_smart);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        mEditCropButton.setOnClickListener(this);


    }
        @Override
        public void onClick(View v) {
            if(v == mEditCropButton) {
                String userInput = mCropEditText.getText().toString();
                //saving location to firebase
                saveUserInputToFirebase(userInput);
                Intent intent = new Intent(FarmSmartActivity.this, FarmProcedureActivity.class);
                intent.putExtra("userInput",userInput);
                startActivity(intent);

            }
        }

        public void saveUserInputToFirebase(String userInput){
        //pass user input as argument to set value in firebase db
        mSearchedCropReference.push().setValue(userInput);
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedCropReference.removeEventListener(mSearchedCropReferenceListener);
    }


}