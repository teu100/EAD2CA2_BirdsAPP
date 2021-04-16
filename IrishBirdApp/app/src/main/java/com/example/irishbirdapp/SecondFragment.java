package com.example.irishbirdapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.MalformedURLException;
import java.net.URL;


public class SecondFragment extends AppCompatActivity {

    private final String TAG = "HelloActivities";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate() called in SecondFragment");
        super.onCreate(savedInstanceState);

        // read data from intent
        Intent intent = getIntent();
        Bird bird = (Bird) getIntent().getSerializableExtra("bird");
        setContentView(R.layout.fragment_second);

        // display data read from intent on a text view
        TextView commonName = findViewById(R.id.CommonNameFS);
        TextView binomial = findViewById(R.id.binomialFS);
        TextView irishName = findViewById(R.id.irishNameFS);
        TextView orderName = findViewById(R.id.orderNameFS);
        TextView familyName = findViewById(R.id.familyNameFS);
        //TextView infoLink = (TextView) findViewById(R.id.info);
        //TextView commonName = (TextView) findViewById(R.id.CommonName);
        String birdName = bird.getCommonName().replace(" ", "");
        commonName.setText(bird.getCommonName());
        binomial.setText(bird.getBinomial());
        irishName.setText(bird.getIrishName());
        orderName.setText(bird.getOrderName());
        familyName.setText(bird.getFamilyName());


        ImageView birdImage = findViewById(R.id.birdImageFS);
        String imageUri = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/63/Rook_at_Slimbridge_Wetland_Centre%2C_Gloucestershire%2C_England_22May2019_arp.jpg/220px-Rook_at_Slimbridge_Wetland_Centre%2C_Gloucestershire%2C_England_22May2019_arp.jpg";
        String shortLink = "https://en.wikipedia.org/wiki/Coal_tit#/media/File:Coal_tit_UK09.JPG";
        Log.d(TAG, "Image Link: "+ bird.getImageLink());
        Picasso.get().load(shortLink).into(birdImage);
   /*
        Drawable drawable;
        drawable = getResources().getDrawable(R.drawable.tryout);
        birdImage.setImageDrawable(drawable);

        ImageView glideTry = findViewById(R.id.birdImageFS);
        String path = "https://www.flickr.com/photos/152270123@N05/51117646103/";
        Log.d(TAG,path);
        Glide.with(this)
                .load("path")
                .into(glideTry);

        URL url = null;

        try {
            url = new URL("https://www.flickr.com/photos/192803554@N06/51119001618/");
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            birdImage.setImageBitmap(bmp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */






    }
}
