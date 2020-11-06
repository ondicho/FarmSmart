package com.moringaschool.farmsmart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Constants;
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


public class FarmListActivity extends AppCompatActivity {
    public static final String TAG= FarmListActivity.class.getSimpleName();
    private List<Datum> crops =new ArrayList<>();
    private List<Datum> description=new ArrayList<>();

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentCrop;


    private CropListAdapter mAdapter;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @BindView(R.id.errorTextView) TextView mErrorTextView;


    @Override                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String userInput = intent.getStringExtra("userInput");
        getPlants(userInput);


        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentCrop=mSharedPreferences.getString(Constants.PREFERENCES_CROP_KEY,null);

        if (mRecentCrop != null) {
            getPlants(mRecentCrop);
        }
    }

//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getPlants(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void getPlants(String userInput){
        final TrefleClient trefleClient=new TrefleClient();
        trefleClient.findCrops(userInput, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call,Response response)  {

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


    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_CROP_KEY, location).apply();
    }
}
