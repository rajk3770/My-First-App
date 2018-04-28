package com.example.raj.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * Created by raj on 13/2/18.
 */

public class MovieDescription extends AppCompatActivity {
    TextView movieTitle, movieDescription;
    ImageView poster1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description);
        movieTitle=(TextView)findViewById(R.id.namelabel);
        movieDescription=(TextView)findViewById(R.id.moviedescription);
        poster1=(ImageView)findViewById(R.id.poster1);
        Intent intent=getIntent();
        String title=intent.getStringExtra("movieTitle");
        String description=intent.getStringExtra("description");
        String moviePosterUri=intent.getStringExtra("posteruri");
        Log.e("Des",description);
        movieTitle.setText(title);
        movieDescription.setText(description);

        Log.e("Trial",moviePosterUri);
        String posterUri=new Uri.Builder()
                .scheme("https")
                .authority("image.tmdb.org")
                .appendPath("t")
                .appendPath("p")
                .appendPath("w342")
                .appendPath(moviePosterUri).build().toString();
        Picasso.with(getApplicationContext())
                .load(posterUri)

                .into(poster1);
    }
}
