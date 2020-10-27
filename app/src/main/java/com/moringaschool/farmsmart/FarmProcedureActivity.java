package com.moringaschool.farmsmart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
    private String[] crops = new String[]{"Cabbages", "Carrots", "Potatoes", "Onions"};
    private String[] description = new String[]{"A leafy green vegetable crop", "A root vegetable, usually orange in color", "A root vegetable that is  a starchy tuber ", "Also known as the bulb onion or common onion, is a vegetable"};
    private static final String TAG = "FarmProcedureActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_procedure);
        ButterKnife.bind(this);

        mListView = (ListView) findViewById(R.id.listView);
        FarmProcedureArrayAdapter adapter = new FarmProcedureArrayAdapter(this, android.R.layout.simple_list_item_1, crops, description);
        mListView.setAdapter(adapter);

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

            }
        });
    }
}
