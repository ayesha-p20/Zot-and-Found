package com.example.zot_and_found;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.zot_and_found.Fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private Button btnLogOut;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnLogOut = findViewById(R.id.btnLogOut);


        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });


        bottomNavigationView = findViewById(R.id.bottomNavigation);
        final FragmentManager fragmentManager = getSupportFragmentManager();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            //This is where we select different fragments
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    //TODO: After adding the individual fragments, reference them correctly here. i.e. instead of setting fragment to be HomeFragment(), set them to be the corresponding fragment to the selection.
                    case R.id.action_home:
                        fragment = new HomeFragment();
                        Log.i(TAG, "You clicked on home");
                        break;
                    case R.id.action_compose:
                        fragment = new HomeFragment();
                        Log.i(TAG, "You clicked on compose");
                        break;
                    case R.id.action_focus:
                    default:
                        fragment = new HomeFragment();
                        Log.i(TAG, "You clicked on focus");
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }

}
