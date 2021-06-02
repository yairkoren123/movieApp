package com.example.drawer_try;

import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer_try.singletonClass.Single_one;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.drawer_try.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {


    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private FirebaseAuth firebaseAuth;

    private FirebaseAuth.AuthStateListener authStateListener;

    private FirebaseUser currentUser;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    private DocumentReference movie_data_add = db.collection("shopping").document();




    String email_now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_slideshow, R.id.nav_setting, R.id.nav_search, R.id.nav_watchlist)
                .setDrawerLayout(drawer)
                .build();


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser = firebaseAuth.getCurrentUser();

                TextView email = findViewById(R.id.email_main);


                if (currentUser != null) {
                    //user is already loggedin..
                    Log.d("cahneg", "onAuthStateChanged: in");
                    Single_one single_one = Single_one.getInstance();
                    single_one = Single_one.getInstance();
                    email_now =  currentUser.getEmail().toString().trim();
                    single_one.setNow_login_email(email_now);
                    //email.setText(email_now);

                }else {
                    //no user yet...
                    Log.d("cahneg", "onAuthStateChanged: exit");
                    Single_one single_one = Single_one.getInstance();
                    single_one = Single_one.getInstance();
                    email_now = "none";
                    single_one.setNow_login_email(email_now);
                }

            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        Single_one single_one = Single_one.getInstance();

        TextView username = findViewById(R.id.username_main);
        //username.setText("hi");

        email_now = single_one.getNow_login_email();
        TextView email = findViewById(R.id.email_main);
        if (email_now == "none"){
            email_now = "no user ";
        }
        email.setText(email_now);


        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        TextView email = findViewById(R.id.email_main);


        // live when data change
        Single_one single_one = Single_one.getInstance();
        email.setText(email_now);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Single_one single_one = Single_one.getInstance();

        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            String email = auth.getCurrentUser().getEmail().toString().trim();
            Log.d("username", "onStart: email:  " + email);
            msg("welcome back  " );
            single_one = Single_one.getInstance();
            single_one.setNow_login_email(email);

        }
        else {
            single_one.setNow_login_email("none");
        }
        auth.addAuthStateListener(authStateListener);

    }
    public void msg(String text){
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG)
                .show();
    }
}
