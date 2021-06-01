package com.example.drawer_try.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.drawer_try.R;
import com.example.drawer_try.databinding.FragmentHomeBinding;
import com.example.drawer_try.modle.FlowerAdapter;
import com.example.drawer_try.modle.The_movies;
import com.example.drawer_try.modle.ViewPagerAdpter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;



    // layout
    ImageView imagemovies;


    ArrayList<String> imagesURLS = new ArrayList<>();


    ArrayList<The_movies> theMoviesArrayList = new ArrayList<>();


    // Will show the string "data" that holds the results
    TextView results;
    // URL of object to be parsed
    //String JsonURL = "https://raw.githubusercontent.com/ianbar20/JSON-Volley-Tutorial/master/Example-JSON-Files/Example-Object.JSON";
    String JsonURL = "https://api.themoviedb.org/3/movie/popular?api_key=2029d84f820b9dc29ab83773c31b4320";
    // This string will hold the results
    String data = "";
    // Defining the Volley request queue that handles the URL request concurrently
    RequestQueue requestQueue;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();



        //imagemovies = findViewById(R.id.imageView);






        // Creates the Volley request queue
        requestQueue = Volley.newRequestQueue(getContext());

        // Casts results into the TextView found within the main layout XML with id jsonData
        //results = (TextView) findViewById(R.id.jsonData);

        // Creating the JsonObjectRequest class called obreq, passing required parameters:
        //GET is used to fetch data from the server, JsonURL is the URL to be fetched from.
        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, JsonURL,null,
                // The third parameter Listener overrides the method onResponse() and passes
                //JSONObject as a parameter
                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            The_movies one_movie = new The_movies();
                            JSONObject jsonObject = new JSONObject();
                            JSONArray jsonArray = response.getJSONArray("results");


                            // use jsonArray.length to get all
                            for (int i = 0; i < 20; i++) {
                                one_movie =  new The_movies();

                                jsonObject = jsonArray.getJSONObject(i);
                                // Retrieves the string labeled "colorName" and "description" from
                                //the response JSON Object
                                //and converts them into javascript objects

                                // get values from URL
                                boolean adult = jsonObject.getBoolean("adult");
                                String title = jsonObject.getString("title");
                                String average = jsonObject.getString("vote_average");
                                String release = jsonObject.getString("release_date");
                                String overview = jsonObject.getString("overview");
                                String original_language = jsonObject.getString("original_language");
                                int vote_count = jsonObject.getInt("vote_count");
                                String image =  jsonObject.getString("poster_path");


                                // put values in class movies
                                one_movie.setAdult(adult);
                                one_movie.setTitle(title);
                                one_movie.setVote_average(average);
                                one_movie.setRelease_date(release);
                                one_movie.setOverview(overview);
                                one_movie.setOriginal_language(original_language);
                                one_movie.setVote_count(vote_count);
                                one_movie.setImage(image);

                                // after all in movies :

                                theMoviesArrayList.add(one_movie);
                                String image_full ="https://image.tmdb.org/t/p/w500" + image ;

                                imagesURLS.add(image_full);



                                // Adds strings from object to the "data" string
                                data = "title Name: " + title +
                                        "average : " + average +
                                        " image : " + image;

                                // Adds the data string to the TextView "results"
                                Log.d("TAG", "onResponse3: "+data);
                                Log.d("TAG", "onResponse3: \n");

                                //results.setText(data);

                            }

                            next_level();


                            Log.d("size", "onResponse: " + theMoviesArrayList.size());

                        }
                        // Try and catch are included to handle any errors due to JSON
                        catch (JSONException e) {
                            // If an error occurs, this prints the error to the log
                            e.printStackTrace();
                        }
                    }
                },
                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                //as a parameter
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        );
        // Adds the JSON object request "obreq" to the request queue
        requestQueue.add(obreq);
        //https://image.tmdb.org/t/p/w500/pKAxHs04yxLDQSIf4MNiZoePVWX.jpg
///pKAxHs04yxLDQSIf4MNiZoePVWX.jpg
//        //https://image.tmdb.org/t/p/w500/
//        Glide.with(getApplicationContext())
//                .load("https://image.tmdb.org/t/p/w500/pKAxHs04yxLDQSIf4MNiZoePVWX.jpg")
//                .into(imagemovies);

    }

    private void next_level(){

        // viewpager
        ViewPager pager_images_movies = getView().findViewById(R.id.viewpager);
        ViewPagerAdpter adpter_pager = new ViewPagerAdpter(getContext(),imagesURLS);
        pager_images_movies.setAdapter(adpter_pager);




        // gridView

        FlowerAdapter adapter;

        GridView gridView = getView().findViewById(R.id.gridview);

        adapter = new FlowerAdapter(getContext(),theMoviesArrayList);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) ->
                msg("click on " + position ));


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