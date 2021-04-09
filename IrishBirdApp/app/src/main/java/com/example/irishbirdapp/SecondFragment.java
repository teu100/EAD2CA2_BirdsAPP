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
        TextView commonName = (TextView) findViewById(R.id.CommonNameFS);
        TextView binomial = (TextView) findViewById(R.id.binomialFS);
        TextView irishName = (TextView) findViewById(R.id.irishNameFS);
        TextView orderName = (TextView) findViewById(R.id.orderNameFS);
        TextView familyName = (TextView) findViewById(R.id.familyNameFS);
        //TextView infoLink = (TextView) findViewById(R.id.info);
        //TextView commonName = (TextView) findViewById(R.id.CommonName);
        String birdName = bird.getCommonName().replace(" ", "");
        commonName.setText(birdName);
        binomial.setText(bird.getBinomial());
        irishName.setText(bird.getIrishName());
        orderName.setText(bird.getOrderName());
        familyName.setText(bird.getFamilyName());


        ImageView birdImage = (ImageView) findViewById(R.id.birdImageFS);
        /*
        Drawable drawable;
        drawable = getResources().getDrawable(R.drawable.tryout);
        birdImage.setImageDrawable(drawable);

        ImageView glideTry = findViewById(R.id.birdImageFS);
        String path = "C:/Users/mateu/Documents/College/EAD2/EAD2CA2_BirdsAPP/IrishBirdApp/app/src/main/res/drawable/tryout";
        Glide.with(this)
                .load("path")
                .into(glideTry);
*/

        birdImage.setImageBitmap(BitmapFactory.decodeFile("/storage/emulated/0/Download/"+birdName+".jpg"));



    }
}
