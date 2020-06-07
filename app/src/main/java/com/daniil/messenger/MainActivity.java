package com.daniil.messenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private ImageView profilepic;
    private SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "mysettings";
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        text = findViewById(R.id.profilename);
        profilepic = findViewById(R.id.imageView);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String id = mSettings.getString("id","");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(id);
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                text.setText(user.nick);
                if(user.linkPhoto.equals("default")){
                    Glide.with(getApplicationContext()).load(R.drawable.defaultprofile).into(profilepic);
                } else{
                    Glide.with(getApplicationContext()).load(user.linkPhoto).into(profilepic);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabase.addValueEventListener(vListener);

    }
}
