package com.example.mymovieapp;
//NOTE: your package will be different than mine!

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mymovieapp.modle.The_movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Movie_Main_Activity extends AppCompatActivity {
    // Will show the string "data" that holds the results
    TextView results;
    // URL of object to be parsed
    //String JsonURL = "https://raw.githubusercontent.com/ianbar20/JSON-Volley-Tutorial/master/Example-JSON-Files/Example-Object.JSON";
    String JsonURL = "https://api.themoviedb.org/3/movie/popular?api_key=2029d84f820b9dc29ab83773c31b4320";
    // This string will hold the results
    String data = "";
    // Defining the Volley request queue that handles the URL request concurrently
    RequestQueue requestQueue;

    ArrayList<The_movies> theMoviesArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_main);
        // Creates the Volley request queue
        requestQueue = Volley.newRequestQueue(this);

        // Casts results into the TextView found within the main layout XML with id jsonData
        results = (TextView) findViewById(R.id.jsonData);

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
                            for (int i = 0; i < 5; i++) {
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


                                // put values in class movies
                                one_movie.setAdult(adult);
                                one_movie.setTitle(title);
                                one_movie.setVote_average(average);
                                one_movie.setRelease_date(release);
                                one_movie.setOverview(overview);
                                one_movie.setOriginal_language(original_language);
                                one_movie.setVote_count(vote_count);

                                // after all in movies :

                                theMoviesArrayList.add(one_movie);



                                // Adds strings from object to the "data" string
                                data = "title Name: " + title +
                                        "average : " + average;

                                // Adds the data string to the TextView "results"
                                Log.d("TAG", "onResponse3: "+data);
                                Log.d("TAG", "onResponse3: \n");

                                //results.setText(data);

                            }
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
    }
}