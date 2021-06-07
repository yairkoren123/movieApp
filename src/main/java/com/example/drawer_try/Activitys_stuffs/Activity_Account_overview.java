package com.example.drawer_try.Activitys_stuffs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer_try.MainActivity;
import com.example.drawer_try.R;
import com.example.drawer_try.modle.The_movies;
import com.example.drawer_try.singletonClass.Single_one;
import com.example.drawer_try.singup.ToData;
import com.example.drawer_try.ui.AddFreind.Search_Account_Fragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Activity_Account_overview extends AppCompatActivity {

    TextView follower_amount, watchlist_amount , username;
    ImageView image_account;
    Button add_follow;
    RecyclerView recyclerView;

    recycler_Adpter_HORIZONTAL adpterHORIZONTAL;


    ArrayList<The_movies> other_array_list = new ArrayList<>();


    // firebase
    Single_one single_one= Single_one.getInstance();

    String email;

    // firebase
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference1 = db.collection("good");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_overview);
        // the over view of the profiles

        follower_amount = findViewById(R.id.textview_followers_ammount_a);
        watchlist_amount = findViewById(R.id.textview_watchlist_ammount_a);
        add_follow = findViewById(R.id.text_acount_follow_add_a);
        username = findViewById(R.id.username_acount_a);
        image_account = findViewById(R.id.imageview_acount_a);
        // recyclerview
        recyclerView = findViewById(R.id.recycler_view_a);

        Bundle extras = getIntent().getExtras();

        email = extras.getString(Search_Account_Fragment.EXTRAOVERVIEW);
        username.setText(email);
        String image = extras.getString(Search_Account_Fragment.EXTRAIMAGE);

        switch (image){
            case "smile_0.png":
                image_account.setBackgroundResource(R.drawable.smile_0);
                break;
            case "smile_1.png":
                image_account.setBackgroundResource(R.drawable.smile_1);
                break;
            case "smile_2.png":
                image_account.setBackgroundResource(R.drawable.smile_2);
                break;
            case "smile_3.png":
                image_account.setBackgroundResource(R.drawable.smile_3);
                break;
            case "smile_4.png":
                image_account.setBackgroundResource(R.drawable.smile_4);
                break;
            case "smile_5.png":
                image_account.setBackgroundResource(R.drawable.smile_5);
                break;
            case "none":
                image_account.setBackgroundResource(R.drawable.smile_7);
                break;
        }

        get_account();

        single_one = Single_one.getInstance();

        boolean isthere = single_one.seeiffollow(email);
        if (isthere == false) {
            // not in the list
            add_follow.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_add_24, 0, 0, 0);
        }else {
            // in the list
            add_follow.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_remove_24, 0, 0, 0);
        }

        add_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // recyclerview

        Single_one single_one = Single_one.getInstance();

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                Activity_Account_overview.this,LinearLayoutManager.HORIZONTAL
                ,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // adpter


    }
    private void get_account(){
        collectionReference1.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (QueryDocumentSnapshot snapshots : queryDocumentSnapshots) {


                    ToData shopping = snapshots.toObject(ToData.class);
                    Log.d("6snal2", "onSuccess: " + email);

                    if (shopping != null) {

                        if (shopping.getEmail().equals(email)) {
                            // if we find the value
                                follower_amount.setText(String.valueOf(shopping.getFriends().size()));
                                watchlist_amount.setText(String.valueOf(shopping.getThe_moviesArrayList().size()));
                                other_array_list = shopping.getThe_moviesArrayList();

                            // adpter

                            adpterHORIZONTAL = new recycler_Adpter_HORIZONTAL(other_array_list,getApplicationContext());
                            recyclerView.setAdapter(adpterHORIZONTAL);

                            Log.d("6data123", "onSuccess: " + shopping.getThe_moviesArrayList().toString());
                            break;
                        }
                    }
                }
            }
        });
    }




    private void msg(String text){
        Toast.makeText(Activity_Account_overview.this,text,Toast.LENGTH_LONG)
                .show();
    }

}