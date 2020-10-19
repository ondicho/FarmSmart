package com.moringaschool.farmsmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

   @BindView(R.id.farmSmartButton) Button mFarmSmartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFarmSmartButton.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if(v == mFarmSmartButton) {
                Intent intent = new Intent(MainActivity.this, FarmSmartActivity.class);
                startActivity(intent);
            }
        }
    }
