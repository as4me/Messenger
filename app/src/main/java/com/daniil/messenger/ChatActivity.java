package com.daniil.messenger;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChatActivity extends AppCompatActivity {
    private Intent intent;
    private FirebaseUser user;
    private DatabaseReference ref;
    private TextView text;
    private ImageView profilepic;
    private EditText chatbox;
    private ImageView sendMessage;
    private String savedMessage;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
        text = findViewById(R.id.chatName);
        profilepic = findViewById(R.id.chatProfilePic);
        Toolbar toolbar = findViewById(R.id.chatToolBar);
        chatbox = findViewById(R.id.chatbox);
        sendMessage = findViewById(R.id.sendmessage);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        intent = getIntent();

        String userId = intent.getStringExtra("userId");
        Log.d("chat",userId);
        ref = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
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
        ref.addValueEventListener(vListener);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!chatbox.getText().toString().matches("")){
                    savedMessage = chatbox.getText().toString();
                } else{
                    Toast.makeText(getApplicationContext(),"Please send message", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
