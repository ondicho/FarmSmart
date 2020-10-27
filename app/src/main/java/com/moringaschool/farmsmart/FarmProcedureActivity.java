package com.moringaschool.farmsmart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.Constants;
import com.models.Datum;
import com.models.TreflePlantSearchResponse;
import com.networking.TrefleApi;
import com.networking.TrefleClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class FarmProcedureActivity extends AppCompatActivity {
    @BindView(R.id.listView)
    ListView mListView;
    private String[] crops ;
    private String[] description;
    private static final String TAG = "FarmProcedureActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_procedure);
        ButterKnife.bind(this);

        mListView = (ListView) findViewById(R.id.listView);
        Intent newIntent = getIntent();
//call back function for API
        Call<List<Datum>> call = TrefleClient.apiInstances().getPlants(Constants.TREFLE_API_KEY);
        call.enqueue(new Callback<List<Datum>>() {
            @Override
            public void onResponse(Call<List<Datum>> call, Response<List<Datum>> response) {
                if (response.isSuccessful()){
                    List<Datum> cropsList=response.body();
                    String[] crops = new String[cropsList.size()];
                    String[] description=new String[cropsList.size()];

                    for (int i=0;i<crops.length;i++){
                        crops[i]=cropsList.get(i).getCommonName();
                    }
                    for (int i=0;i<description.length;i++){
                        description[i]=cropsList.get(i).getScientificName();
                    }
//array adapter for the API
                    ArrayAdapter adapter=new FarmProcedureArrayAdapter(FarmProcedureActivity.this, android.R.layout.simple_list_item_1,crops,description);
                    mListView.setAdapter(adapter);
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
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

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


}
