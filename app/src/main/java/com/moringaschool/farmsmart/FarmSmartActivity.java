package com.moringaschool.farmsmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FarmSmartActivity extends AppCompatActivity {

    private EditText mCropEditText;
    private Button mEditCropButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_smart);

        Intent intent = getIntent();

        mCropEditText=(EditText) findViewById(R.id.cropEditText);
        mEditCropButton=(Button)findViewById(R.id.editCropButton);
        mEditCropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cropPreference=mCropEditText.getText().toString();
                Intent newIntent=new Intent(FarmSmartActivity.this,FarmProcedureActivity.class);
                newIntent.putExtra("cropPreference",cropPreference);
                startActivity(newIntent);
            }
        });


    }
}