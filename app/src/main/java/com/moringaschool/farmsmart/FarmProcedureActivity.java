package com.moringaschool.farmsmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FarmProcedureActivity extends AppCompatActivity {
    @BindView(R.id.listView) ListView mListView;
    private String [] crops=new String[]{"Cabbages","Carrots","Potatoes","Onions"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_procedure);
        ButterKnife.bind(this);

        mListView = (ListView) findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, crops);
        mListView.setAdapter(adapter);

        Intent newIntent=getIntent();
    }
}