package com.daniil.messenger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daniil.messenger.Fragments.MessagesFragment;
import com.daniil.messenger.Fragments.ProfileFragment;
import com.daniil.messenger.Fragments.UsersListFragment;
import com.daniil.messenger.Models.User;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private ImageView profilepic;
    private SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "mysettings";
    private DatabaseReference mDatabase;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        text = findViewById(R.id.profilename);
        profilepic = findViewById(R.id.imageView);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        editor = mSettings.edit();

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
        TabLayout tabLayout = findViewById(R.id.tablayout);
        ViewPager viewPager = findViewById(R.id.viewpager);
        ViewPagerAdapter viewAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewAdapter.addFragment(new MessagesFragment(), "Chats");
        viewAdapter.addFragment(new UsersListFragment(), "Users");
        viewAdapter.addFragment(new ProfileFragment(), "User Profile");
        viewPager.setAdapter(viewAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout :
                mSettings.edit().clear().commit();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), StartedActivity.class);

                startActivity(intent);
                finish();
                return true;
        }
        return false;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter{

        private ArrayList<Fragment> fragment;
        private ArrayList<String> titles;

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragment.get(position);
        }
        public ViewPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
            this.fragment = new ArrayList<>();
            this.titles = new ArrayList<>();
        }

        @Override
        public int getCount() {
            return fragment.size();
        }
        public void addFragment(Fragment f, String title){
            fragment.add(f);
            titles.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

}
