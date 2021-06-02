package com.example.drawer_try.ui.gallery;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.drawer_try.R;
import com.example.drawer_try.databinding.FragmentGalleryBinding;
import com.example.drawer_try.modle.FlowerAdapter;
import com.example.drawer_try.modle.Fragment_the_movie_overview;
import com.example.drawer_try.modle.The_movies;
import com.example.drawer_try.singletonClass.Single_one;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    //watchlist

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;

    private Context context;

    FlowerAdapter adapter;

    private ArrayList<The_movies> the_moviesArrayList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();

        Single_one single_one = Single_one.getInstance();
        the_moviesArrayList = single_one.getThe_love_movies();

        LinearLayout no_account = getView().findViewById(R.id.no_account_linar);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        no_account.setVisibility(View.GONE);

        FrameLayout frameLayout = getView().findViewById(R.id.mail_countener5);
        frameLayout.setVisibility(View.VISIBLE);

        GridView gridView = getView().findViewById(R.id.gridview_love_list);
        gridView.setVisibility(View.VISIBLE);



        if (auth.getCurrentUser() == null || single_one.getNow_login_email() == "none") {
            no_account.setVisibility(View.VISIBLE);
            frameLayout.setVisibility(View.GONE);
            gridView.setVisibility(View.GONE);

            Log.d("vis", "onViewCreated: ");
        }
            msg("you are login already");
        // adpter

        adapter = new FlowerAdapter(getContext(),the_moviesArrayList);
        gridView.setAdapter(adapter);






        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                msg("click on " + the_moviesArrayList.get(position).getTitle());
                single_one.setValue_movie(the_moviesArrayList.get(position));


                Fragment_the_movie_overview nextFrag= new Fragment_the_movie_overview();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.mail_countener5, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();

//                gridView.setVisibility(View.GONE);
//                pager_images_movies.setVisibility(View.GONE);

            }
        });


    }


    private void msg(String text){
        Toast.makeText(getContext(),text,Toast.LENGTH_LONG)
                .show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}