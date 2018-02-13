package com.example.raj.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by raj on 13/2/18.
 */

public class MovieDescription extends AppCompatActivity {
    TextView movieTitle, movieDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description);
        movieTitle=(TextView)findViewById(R.id.namelabel);
        movieDescription=(TextView)findViewById(R.id.moviedescription);
        Intent intent=getIntent();
        String title=intent.getStringExtra("movieTitle");
        String description=intent.getStringExtra("description");
        Log.e("Des",description);
        movieTitle.setText(title);
        movieDescription.setText(description);
    }
}
