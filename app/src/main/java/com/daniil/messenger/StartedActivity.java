package com.daniil.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartedActivity extends AppCompatActivity {

    private SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "mysettings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        boolean bool = mSettings.getBoolean("authstate",false);
        Log.d("check", String.valueOf(bool));
        if(bool){
            Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);
        }
        else{
            setContentView(R.layout.activity_main);
            ButterKnife.bind(this);
        }
    }
    @OnClick(R.id.register)
    public void submit(){
        Toast.makeText(getApplicationContext(),"test2",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, RegisterProcess.class);

        startActivity(intent);
    }
    @OnClick(R.id.signin)
    public void enter(){
        Toast.makeText(getApplicationContext(),"test1",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, LoginProcess.class);

        startActivity(intent);
    }
}
