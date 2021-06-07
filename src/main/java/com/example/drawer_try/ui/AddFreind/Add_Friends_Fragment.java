package com.example.drawer_try.ui.AddFreind;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer_try.R;
import com.example.drawer_try.databinding.AddFriendsFragmentBinding;
import com.example.drawer_try.databinding.SearchFragmentBinding;
import com.example.drawer_try.modle.The_friend;
import com.example.drawer_try.singletonClass.Single_one;
import com.example.drawer_try.singup.ToData;
import com.example.drawer_try.ui.Search.SearchBox;
import com.example.drawer_try.ui.Search.SearchViewModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Add_Friends_Fragment extends Fragment {


    //layout
    private EditText search_account_by_name_et;
    private ImageView search_account_image;


    private AddFriendsViewModel mViewModel;

    private AddFriendsViewModel addFriendsViewModel;
    private AddFriendsFragmentBinding binding;
    private ArrayList<ToData> friendArrayList;

    String the_user_Image = "";
    String text = "";
    boolean search_by_text = false;



    // firebase
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference1 = db.collection("good");



    //firestore / firebase
    private FirebaseAuth auth = FirebaseAuth.getInstance();


    public static Add_Friends_Fragment newInstance() {
        return new Add_Friends_Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        addFriendsViewModel =
                new ViewModelProvider(this).get(AddFriendsViewModel.class);

        binding = AddFriendsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        auth = FirebaseAuth.getInstance();

        // layout
        search_account_by_name_et = view.findViewById(R.id.edittext_search_friends);
        search_account_image = view.findViewById(R.id.imageView_search_friends);

        search_account_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = search_account_by_name_et.getText().toString().trim();

                    if (text.contains("@")){
                        friendArrayList = new ArrayList<>();

                        Log.d("6cou", "onClick: text have '@' text : " + text);
                        search_by_text = true;


                    }
                call_frag();

                }
        });


        friendArrayList = new ArrayList<>();


        // GET ALL THE USERS / or     - > by text by starting fragment
        call_frag();


    }
    public void call_frag(){
        Search_Account_Fragment nextFrag = new Search_Account_Fragment(text);
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.mail_countener7, nextFrag, "findThisFragment")
                .addToBackStack(null)
                .commit();
    }





        @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddFriendsViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void msg(String text){
        Toast.makeText(getContext(),text,Toast.LENGTH_LONG)
                .show();
    }
}

