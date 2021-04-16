package com.example.irishbirdapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.MalformedURLException;
import java.net.URL;


public class SecondFragment extends AppCompatActivity {

    private final String TAG = "SecondFragment";
    private String LIKE_UNLIKE_URI = "https://ead2ca2birdappapi20210409142733.azurewebsites.net/api/birds/likeUnlike?id=";
    private String RANDOM_BIRD_URI = "https://ead2ca2birdappapi20210409142733.azurewebsites.net/api/birds/randomBird";
    private int currentBirdID;
    public final static Bird REVIEW_MESSAGE=  new Bird();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate() called in SecondFragment");
        super.onCreate(savedInstanceState);



        // read data from intent
        Intent intent = getIntent();
        Bird bird = (Bird) getIntent().getSerializableExtra("bird");
        setContentView(R.layout.fragment_second);

        currentBirdID = bird.getID();
        // display data read from intent on a text view
        TextView commonName = findViewById(R.id.CommonNameFS);
        TextView binomial = findViewById(R.id.binomialFS);
        TextView irishName = findViewById(R.id.irishNameFS);
        TextView orderName = findViewById(R.id.orderNameFS);
        TextView familyName = findViewById(R.id.familyNameFS);
        TextView infoLink =  findViewById(R.id.infoLinkFS);
        TextView liked =  findViewById(R.id.likedFS);
        //TextView commonName = (TextView) findViewById(R.id.CommonName);
        String birdName = bird.getCommonName().replace(" ", "");
        commonName.setText(bird.getCommonName());
        binomial.setText(bird.getBinomial());
        irishName.setText(bird.getIrishName());
        orderName.setText(bird.getOrderName());
        familyName.setText(bird.getFamilyName());
        infoLink.setText(bird.getInfoLink());
        liked.setText(bird.getLiked());


        ImageView birdImage = findViewById(R.id.birdImageFS);
        String imageUri =  bird.getImageLink() ;
        //String shortLink = "https://en.wikipedia.org/wiki/Coal_tit#/media/File:Coal_tit_UK09.JPG";
        Log.d(TAG, "Image Link: "+ bird.getImageLink());
        Picasso.get().load(imageUri).into(birdImage);
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

    public void birdInfo(Bird bird){
        //REVIEW_MESSAGE = bird;
        String[] reviewMessage = new String[7];

        String commonName = bird.getCommonName();
        Intent intent = new Intent(this, SecondFragment.class);
        intent.putExtra("bird", bird);
        startActivity(intent);

    }



    public void callService(View view){

        final TextView outputBirds = (TextView) findViewById(R.id.outputTextView);
        try{
            //making a string request
            RequestQueue queue = Volley.newRequestQueue(this);
            Log.d(TAG, "Making request");
            try{
                StringRequest strObjRequest = new StringRequest(Request.Method.GET, RANDOM_BIRD_URI,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response)
                            {
                                Gson gson = new Gson();
                                Bird[] bird = gson.fromJson(response, Bird[].class);
                                birdInfo(bird[0]);
                                Log.d(TAG, bird[0].getBinomial());
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {

                                Log.d(TAG, "Error" + error.toString());
                            }
                        });
                queue.add(strObjRequest);           // can have multiple in a queue, and can cancel
            }
            catch(Exception e1) {
                Log.d(TAG, e1.toString());

            }
        }
        catch (Exception e2) {
            Log.d(TAG, e2.toString());

        }
    }

    public void likeUnlikeBird(View view){

        final TextView outputBirds = (TextView) findViewById(R.id.outputTextView);
        String likeUnlikeURI = LIKE_UNLIKE_URI+String.valueOf(currentBirdID);
        try{
            //making a string request
            RequestQueue queue = Volley.newRequestQueue(this);
            Log.d(TAG, "Making request");
            Log.d(TAG, likeUnlikeURI);
            Log.d(TAG, String.valueOf(currentBirdID));
            try{
                StringRequest strObjRequest = new StringRequest(Request.Method.PUT, likeUnlikeURI,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response)
                            {
                                
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {

                                Log.d(TAG, "Error" + error.toString());
                            }
                        });
                queue.add(strObjRequest);           // can have multiple in a queue, and can cancel
            }
            catch(Exception e1) {
                Log.d(TAG, e1.toString());

            }
        }
        catch (Exception e2) {
            Log.d(TAG, e2.toString());

        }
    }


}

