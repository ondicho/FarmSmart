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

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class FarmProcedureActivity extends AppCompatActivity {
    public static final String TAG=FarmProcedureActivity.class.getSimpleName();
    private List<Datum> crops =new ArrayList<>();
    private List<Datum> description=new ArrayList<>();
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private CropListAdapter mAdapter;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
//    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    @Override                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_procedure);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String userInput = intent.getStringExtra("userInput");
        getPlants(userInput);
    }

    private void getPlants(String userInput){
        final TrefleClient trefleClient=new TrefleClient();
        trefleClient.findCrops(userInput, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                crops=trefleClient.processResults(response);
                FarmProcedureActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter=new CropListAdapter(getApplicationContext(),crops);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(FarmProcedureActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
