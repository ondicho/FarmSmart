package com.moringaschool.farmsmart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adapters.CropListAdapter;
import com.models.Datum;
import com.models.TreflePlantSearchResponse;
import com.networking.TrefleApi;
import com.networking.TrefleClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FarmProcedureActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
//    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    private CropListAdapter mAdapter;
    private List<Datum> crops =new ArrayList<>();
    private String[] description;
    private static final String TAG = "FarmProcedureActivity";

    @Override                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_procedure);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String userInput = intent.getStringExtra("userInput");
        TrefleApi client=TrefleClient.getPlants();

        Call<TreflePlantSearchResponse> call=client.getPlants("userInput");
        call.enqueue(new Callback<TreflePlantSearchResponse>() {
            @Override
            public void onResponse(Call<TreflePlantSearchResponse> call, Response<TreflePlantSearchResponse> response) {
                if(response.isSuccessful()){
                    crops=response.body().getData();
                    mAdapter=new CropListAdapter(FarmProcedureActivity.this,crops);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(FarmProcedureActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showCrops();
                }else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<TreflePlantSearchResponse> call, Throwable t) {
                showFailureMessage();
            }
        });
    }


    private void showFailureMessage() {
        mErrorTextView.setText("Ooops!! Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }
//
    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Ooops!!. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }
//
//
////    private void hideProgressBar() {
////        mProgressBar.setVisibility(View.GONE);
////    }
//
    private void showCrops() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }


}