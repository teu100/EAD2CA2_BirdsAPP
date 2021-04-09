package com.example.irishbirdapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


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


        commonName.setText(bird.getCommonName());
        binomial.setText(bird.getBinomial());
        irishName.setText(bird.getIrishName());
        orderName.setText(bird.getOrderName());
        familyName.setText(bird.getFamilyName());

    }
}
