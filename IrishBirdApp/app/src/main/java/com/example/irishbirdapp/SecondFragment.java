package com.example.irishbirdapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;


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
*/

        birdImage.setImageBitmap(BitmapFactory.decodeFile("https://www.flickr.com/photos/152270123@N05/51117646103/"));



    }
}
