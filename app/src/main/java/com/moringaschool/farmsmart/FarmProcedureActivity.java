package com.moringaschool.farmsmart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adapters.CropListAdapter;
import com.adapters.FarmProcedureArrayAdapter;
import com.models.Datum;
import com.networking.TrefleApi;
import com.networking.TrefleClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class FarmProcedureActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    private CropListAdapter mAdapter;
    private List<Datum> crops ;
    private String[] description;
    private static final String TAG = "FarmProcedureActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_procedure);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String userInput= intent.getStringExtra("userInput");

//        TrefleApi client=TrefleApi.getClient();
//call back function for API
        Call<List<Datum>> call = TrefleClient.apiInstances().getPlants(userInput);
        call.enqueue(new Callback<List<Datum>>() {
            @Override
            public void onResponse(Call<List<Datum>> call, Response<List<Datum>> response) {
                if (response.isSuccessful()){
                  crops=response.body();
                  mAdapter=new CropListAdapter(FarmProcedureActivity.this,crops);
                  mRecyclerView.setAdapter(mAdapter);
                  RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(FarmProcedureActivity.this);
                  mRecyclerView.setLayoutManager(layoutManager);
                  mRecyclerView.setHasFixedSize(true);

                  showCrops();

                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<List<Datum>> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
                hideProgressBar();
                showFailureMessage();
            }
        });

    }

    private void showFailureMessage() {
        mErrorTextView.setText("Ooops!! Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Ooops!!. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }


    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    private void showCrops() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

}
