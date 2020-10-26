package com.moringaschool.farmsmart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.Constants;
import com.models.Datum;
import com.models.TreflePlantSearchResponse;
import com.networking.TrefleApi;
import com.networking.TrefleClient;

import java.util.List;

import javax.security.auth.callback.Callback;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;



public class FarmProcedureActivity extends AppCompatActivity{
    @BindView(R.id.listView) ListView mListView;
    private String [] crops=new String[]{"Cabbages","Carrots","Potatoes","Onions"};
    private String [] description=new String[]{"A leafy green vegetable crop","A root vegetable, usually orange in color","A root vegetable that is  a starchy tuber ","Also known as the bulb onion or common onion, is a vegetable"};
    private static final String TAG="FarmProcedureActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_procedure);
        ButterKnife.bind(this);

        mListView = (ListView) findViewById(R.id.listView);
        FarmProcedureArrayAdapter adapter = new FarmProcedureArrayAdapter(this, android.R.layout.simple_list_item_1, crops, description);
        mListView.setAdapter(adapter);

        Intent newIntent = getIntent();

        Call<List<Datum>> call = TrefleClient.apiInstances().getPlants(Constants.TREFLE_API_KEY);
        call.enqueue((retrofit2.Callback<List<Datum>>) this);
    }

            public void onResposne(Call<List<Datum>> call,
                Response<List<Datum>>response){
                    if (response.isSuccessful()) {
                        Log.i(TAG, "onResponse:Success");
                }
            }

            public void onFailure(Call <List<Datum>> call, Throwable t) {
                Log.e(TAG,"onFailure:Error",t);
            }
}
