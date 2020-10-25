package com.moringaschool.farmsmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

public class FarmProcedureActivity extends AppCompatActivity {
    @BindView(R.id.listView) ListView mListView;
    private String [] crops=new String[]{"Cabbages","Carrots","Potatoes","Onions"};
    private String [] description=new String[]{"A leafy green vegetable crop","A root vegetable, usually orange in color","A root vegetable that is  a starchy tuber ","Also known as the bulb onion or common onion, is a vegetable"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_procedure);
        ButterKnife.bind(this);

        mListView = (ListView) findViewById(R.id.listView);
        FarmProcedureArrayAdapter adapter = new FarmProcedureArrayAdapter(this, android.R.layout.simple_list_item_1, crops,description);
        mListView.setAdapter(adapter);

        Intent newIntent=getIntent();
    }

    TrefleClient.apiInstances().getPlants( Constants.TREFLE_API_KEY)
}