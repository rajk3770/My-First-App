package com.example.raj.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView poster;
    TextView movieName;
    String movieTitle,movieDesc,moviePosterUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        poster = (ImageView) findViewById(R.id.poster);
        movieName = (TextView) findViewById(R.id.moviename);
        poster.setOnClickListener(this);

        String url = new Uri.Builder()
                .scheme("https")
                .appendPath("api.themoviedb.org")
                .appendPath("3")
                .appendPath("discover")
                .appendPath("movie")
                .appendQueryParameter("api_key", "9b184480dbdff99fe5b9c7fcb12ba01d")
                .build()
                .toString();



        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            Log.e("test", response.toString(2));
                            JSONArray movies=response.getJSONArray("results");

                            JSONObject popularMovie = movies.getJSONObject(0);
                            movieTitle=popularMovie.getString("title");
                            movieDesc=popularMovie.getString("overview");
                            moviePosterUri=popularMovie.getString("poster_path");
                            moviePosterUri=moviePosterUri.substring(1);
                            Log.e("movieTitle",movieTitle);
                            Log.e("movieDesc",movieDesc);
                            Log.e("moviePoster",moviePosterUri);
                            String posterUri=new Uri.Builder()
                                    .scheme("https")
                                    .authority("image.tmdb.org")
                                    .appendPath("t")
                                    .appendPath("p")
                                    .appendPath("w342")
                                    .appendPath(moviePosterUri).build().toString();
                            Picasso.with(getApplicationContext())
                                    .load(posterUri)

                                    .into(poster);


                            movieName.setText(movieTitle);
                        }
                        catch(JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub.e
                        Log.e("Test", error.getMessage());

                    }
                });

        AppController.getInstance().addToRequestQueue(jsObjRequest);
    }


    @Override
    public void onClick(View view) {
        Intent i = new Intent(MainActivity.this, MovieDescription.class);
        i.putExtra("movieTitle",movieTitle);
        i.putExtra("description",movieDesc);
        i.putExtra("posteruri", moviePosterUri);
        Log.e("moviedes",movieDesc);
        startActivity(i);
    }
}

