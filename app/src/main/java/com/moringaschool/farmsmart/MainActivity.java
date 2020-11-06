package com.moringaschool.farmsmart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    @BindView(R.id.findCropButton) Button mFindCropButton;
    @BindView(R.id.savedCropsbutton) Button mSavedCropsButton;

    @BindView(R.id.cropEditText) EditText mCropEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        mSharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        mEditor=mSharedPreferences.edit();

        mFindCropButton.setOnClickListener(this);
        mSavedCropsButton.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        if(v == mFindCropButton) {
            String userInput=mCropEditText.getText().toString();

            Intent intent=new Intent(MainActivity.this,FarmListActivity.class);
            intent.putExtra("userInput",userInput);
            startActivity(intent);
        }

        if(v == mSavedCropsButton) {
            Intent intent = new Intent(MainActivity.this, SavedCropListActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_logout){
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}






