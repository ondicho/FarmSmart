package com.moringaschool.farmsmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class FarmSmartActivity extends AppCompatActivity  implements View.OnClickListener {

    @BindView(R.id.cropEditText) EditText mCropEditText;
    @BindView(R.id.editCropButton) Button mEditCropButton;

    private static final String TAG = "FarmSmartActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_smart);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        mEditCropButton.setOnClickListener(this);
    }
        @Override
        public void onClick(View v) {
            if(v == mEditCropButton) {
                Intent intent = new Intent(FarmSmartActivity.this, FarmProcedureActivity.class);
                startActivity(intent);

            }
        }


}