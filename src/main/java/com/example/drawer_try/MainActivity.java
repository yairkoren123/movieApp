package com.example.drawer_try;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer_try.modle.The_movies;
import com.example.drawer_try.singletonClass.Single_one;
import com.example.drawer_try.singup.About;
import com.example.drawer_try.singup.ToData;
import com.google.android.gms.tasks.OnSuccessListener;
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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    // progressDialog
    ProgressDialog progressDialog;





    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private FirebaseFirestore firebaseFirestore;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    //private DocumentReference journalRef = db.document("Journal/First Thoughts");
    private DocumentReference cool = db.collection("good")
            .document("First Thoughts");
    private CollectionReference collectionReference1 = db.collection("good");

    private DocumentReference movie_data_add = db.collection("good").document();

    private FirebaseAuth firebaseAuth;

    private FirebaseAuth.AuthStateListener authStateListener;

    private FirebaseUser currentUser;






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
                    Log.d("change", "onAuthStateChanged: exit");
                    Single_one single_one = Single_one.getInstance();
                    single_one = Single_one.getInstance();
                    email_now = "none";
                    single_one.setNow_login_email(email_now);
                }

            }
        };
    }
    private static int RESULT_LOAD_IMG = 1;
    ImageView imagemain;

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


        imagemain = findViewById(R.id.imageView_main);

        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);

            }
        });

        return true;
    }

  @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                // todo user image save
                // set in single the user image
                Single_one single_one = Single_one.getInstance();
                single_one.setUserImage(selectedImage);


                Log.d("image1", "onActivityResult: " + selectedImage);
                DocumentReference documentReference = db.collection("good").document(email_now);



                imagemain.setImageBitmap(selectedImage);

                msg("image set!");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_about:
                msg("is action_about");
                Intent intent = new Intent(this, About.class);
                startActivity(intent);

                break;

            case R.id.action_Login:
                msg("is action_Login");

                //todo need to take the user to the login page
                break;

            case R.id.action_Logout:
                //msg("is action_Logout");
//                FirebaseAuth auth = FirebaseAuth.getInstance();
//
//                if (auth.getCurrentUser() == null){
//                    msg("you don't have a user to logout");
//
//

//                }else {
//                    auth = FirebaseAuth.getInstance();
//                    auth.signOut();
//                    Single_one single_one = Single_one.getInstance();
//                    The_movies one_none_move = new The_movies();
//                    one_none_move.setTitle("none");
//                    ArrayList<The_movies> s = new ArrayList<>();
//                    s.add(one_none_move);
//                    single_one.setThe_love_movies(s);
//                    single_one.setNow_login_email("none");
//                    msg("now your are out");
//
//                    intent = new Intent(this, MainActivity.class);
//                    startActivity(intent);
//                    break;
//                }


        }


        return super.onOptionsItemSelected(item);
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
            msg("welcome back  ");
            single_one = Single_one.getInstance();
            single_one.setNow_login_email(email);

            ToData data = new ToData();
            The_movies the_movies = new The_movies();


//            db.collection("good")
//                    .get()
//                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                            if (task.isSuccessful()) {
//                                for (QueryDocumentSnapshot document : task.getResult()) {
//                                    Log.d("popo", document.getId() + " => " + document.getData());
//                                    Log.d("dec", "onComplete: "+document.getId());
//                                    ArrayList<The_movies> z = new ArrayList<>();
//                                    z = (ArrayList) document.getData().getOrDefault("the_moviesArrayList","true");
//                                    Log.d("dec1", "onComplete: "+z.get(0));
//                                    The_movies the_movies1 = new The_movies();
//                                    The_movies xd = new The_movies();
//                                    xd = z.get(0);
//
//                                }
//                            } else {
//                                Log.d("popo", "Error getting documents: ", task.getException());
//                            }
//                        }
//                    });




            collectionReference1.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                    String data = "";
                    for (QueryDocumentSnapshot snapshots : queryDocumentSnapshots) {

                        Log.d("sec", "onSuccess: " + snapshots.getId());

                        ToData shopping = snapshots.toObject(ToData.class);
                        Log.d("snal2", "onSuccess: " +email);

                        if (shopping.getEmail().equals(email)){
                            Log.d("snal", "onSuccess: " +snapshots.toString());
                            Log.d("sec", "onSuccess: target on : " + snapshots.getId());

                            Log.d("sec2", "onSuccess: target on : " + shopping.getEmail());

                            Single_one single_one = Single_one.getInstance();
                            single_one.setThe_love_movies(shopping.getThe_moviesArrayList());

                            Log.d("data", "onSuccess: " + shopping.getThe_moviesArrayList().toString());
                        }

                    }
                }
            });
        }else {
            single_one.setNow_login_email("none");
        }
        auth.addAuthStateListener(authStateListener);

    }
    // progressDialog

    public void progressDialog(){
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.show();
        // set Content view
        progressDialog.setContentView(R.layout.progress_dialog);
        // set Transparent background
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );
    }
    public void msg(String text){
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onBackPressed() {

        // dsmiss progress dialog
        //progressDialog.dismiss();

        msg("no");
//        Intent intent = new Intent(this,MainActivity.class);
//        startActivity(intent);
        super.onBackPressed();
    }
}
