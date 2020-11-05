package com.moringaschool.farmsmart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adapters.CropListAdapter;
import com.models.Datum;
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


public class FarmListActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG= FarmListActivity.class.getSimpleName();
    private List<Datum> crops =new ArrayList<>();
    private List<Datum> description=new ArrayList<>();
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private CropListAdapter mAdapter;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.savedCropsbutton) Button mSavedCropsButton;

    @Override                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String userInput = intent.getStringExtra("userInput");
        getPlants(userInput);
        mSavedCropsButton.setOnClickListener(this);

    }

    private void getPlants(String userInput){
        final TrefleClient trefleClient=new TrefleClient();
        trefleClient.findCrops(userInput, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call,Response response) throws IOException {
                crops=trefleClient.processResults(response);
                FarmListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter=new CropListAdapter(getApplicationContext(),crops);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(FarmListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v==mSavedCropsButton){
            Intent intent=new Intent(FarmListActivity.this,SavedCropListActivity.class);
            startActivity(intent);
        }

    }
}
