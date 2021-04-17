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
import android.widget.EditText;
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
import java.util.ArrayList;


public class SecondFragment extends AppCompatActivity {

    private final String TAG = "SecondFragment";
    private String LIKE_UNLIKE_URI = "https://ead2ca2birdappapi20210409142733.azurewebsites.net/api/birds/likeUnlike?id=";
    private String RANDOM_BIRD_URI = "https://ead2ca2birdappapi20210409142733.azurewebsites.net/api/birds/randomBird";
    private String FAMILY_BIRDS_URI = "https://ead2ca2birdappapi20210409142733.azurewebsites.net/api/Birds/familyName?familyName=";
    private String SEARCH_URI = "https://ead2ca2birdappapi20210409142733.azurewebsites.net/api/birds/search?searchTerm=";


    private int currentBirdID;
    public final static Bird REVIEW_MESSAGE=  new Bird();
    private FamilyBird familyBirds;

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

        this.familyBirds(bird.getFamilyName());
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

    public void familyBirds(String familyName){
        final TextView outputBirds = (TextView) findViewById(R.id.outputTextView);
        String searchUrl = FAMILY_BIRDS_URI+familyName;

        try{
            //making a string request
            RequestQueue queue = Volley.newRequestQueue(SecondFragment.this);
            Log.d(TAG, "Making request");
            Log.d(TAG, searchUrl);
            try{
                StringRequest strObjRequest = new StringRequest(Request.Method.GET, searchUrl,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response)
                            {
                                Gson gson = new Gson();
                                FamilyBird[] familyBirds = gson.fromJson(response, FamilyBird[].class);
                                ArrayList<FamilyBird> notTheSameIDBirds = new ArrayList<FamilyBird>();
                                for(int i =0; i< familyBirds.length; i++){//for every bird that I get from the api call
                                    if(familyBirds[i].getID() != currentBirdID){//I am making sure that none of them is the same as the bird that is in display already
                                        notTheSameIDBirds.add(familyBirds[i]);
                                    }
                                }
                                if(notTheSameIDBirds.size() == 0){//if the list is = 0 then make the pictures & texts for family birds are set to invisible
                                    TextView FBCName1 = findViewById(R.id.FBCName1);
                                    TextView FBCName2 = findViewById(R.id.FBCName2);
                                    ImageView FBPicture1 = findViewById(R.id.FBPicture1);
                                    ImageView FBPicture2 = findViewById(R.id.FBPicture2);

                                    FBCName1.setVisibility(View.INVISIBLE);
                                    FBCName2.setVisibility(View.INVISIBLE);

                                    FBPicture1.setVisibility(View.INVISIBLE);
                                    FBPicture2.setVisibility(View.INVISIBLE);
                                }
                                else if(notTheSameIDBirds.size() == 1){//if the list is = 1 then make the picture & text for the second family bird invisible
                                    TextView FBCName2 = findViewById(R.id.FBCName2);
                                    ImageView FBPicture2 = findViewById(R.id.FBPicture2);
                                    FBCName2.setVisibility(View.INVISIBLE);
                                    FBPicture2.setVisibility(View.INVISIBLE);

                                    TextView FBCName1 = findViewById(R.id.FBCName1);
                                    ImageView FBPicture1 = findViewById(R.id.FBPicture1);
                                    FBCName1.setText(notTheSameIDBirds.get(0).getCommonName());
                                    String imageUri1 =  notTheSameIDBirds.get(0).getImageLink() ;
                                    Picasso.get().load(imageUri1).into(FBPicture1);

                                }
                                else{
                                    TextView FBCName1 = findViewById(R.id.FBCName1);
                                    TextView FBCName2 = findViewById(R.id.FBCName2);

                                    ImageView FBPicture1 = findViewById(R.id.FBPicture1);
                                    ImageView FBPicture2 = findViewById(R.id.FBPicture2);

                                    FBCName1.setText(notTheSameIDBirds.get(0).getCommonName());
                                    FBCName2.setText(notTheSameIDBirds.get(1).getCommonName());

                                    String imageUri1 = notTheSameIDBirds.get(0).getImageLink();
                                    Picasso.get().load(imageUri1).into(FBPicture1);

                                    String imageUri2 = notTheSameIDBirds.get(1).getImageLink();
                                    Picasso.get().load(imageUri2).into(FBPicture2);
                                }

                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                Log.d(TAG, "onErrorResponse: ", error);

                            }
                        });
                queue.add(strObjRequest);
            }
            catch(Exception e1) {
                Log.d("Exception : ", e1.toString());

            }
        }
        catch (Exception e2) {
            Log.d("Exception 2", e2.toString());

        }
    }

    //after all there was no need for this method
    public void familyBirdInfo(ArrayList<FamilyBird> familyBird1){
        if(familyBird1.size() == 1){
            TextView FBCName1 = findViewById(R.id.FBCName1);
            ImageView FBPicture1 = findViewById(R.id.FBPicture1);
            FBCName1.setText(familyBird1.get(0).getCommonName());
            String imageUri1 =  familyBird1.get(0).getImageLink() ;
            Picasso.get().load(imageUri1).into(FBPicture1);
        }else {
            TextView FBCName1 = findViewById(R.id.FBCName1);
            TextView FBCName2 = findViewById(R.id.FBCName2);

            ImageView FBPicture1 = findViewById(R.id.FBPicture1);
            ImageView FBPicture2 = findViewById(R.id.FBPicture2);

            FBCName1.setText(familyBird1.get(0).getCommonName());
            FBCName2.setText(familyBird1.get(1).getCommonName());

            String imageUri1 = familyBird1.get(0).getImageLink();
            Picasso.get().load(imageUri1).into(FBPicture1);

            String imageUri2 = familyBird1.get(1).getImageLink();
            Picasso.get().load(imageUri2).into(FBPicture2);
        }

    }


    public void fB1Click(View view){
        TextView FBCName1 = findViewById(R.id.FBCName1);
        String birdName = FBCName1.getText().toString();
        birdName = birdName.replace(" ", "%20");
        Log.d( "fB1Click: ", birdName);
        this.searchBirdbyImageCLick(birdName);

    }

    public void fB2Click(View view){
        TextView FBCName2 = findViewById(R.id.FBCName2);
        String birdName = FBCName2.getText().toString();
        birdName = birdName.replace(" ", "%20");
        Log.d( "fB2Click: ", birdName);
        this.searchBirdbyImageCLick(birdName);

    }

    public void searchBirdbyImageCLick(String birdName){
        String searchUrl = SEARCH_URI+birdName;

        try{
            //making a string request
            RequestQueue queue = Volley.newRequestQueue(SecondFragment.this);
            Log.d(TAG, "Making request");
            Log.d("Bird name : ", birdName);
            Log.d("Search url : ", searchUrl);
            try{
                StringRequest strObjRequest = new StringRequest(Request.Method.GET, searchUrl,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response)
                            {
                                Gson gson = new Gson();
                                Bird bird = gson.fromJson(response, Bird.class);
                                birdInfo(bird);

                                Log.d(TAG, searchUrl);
                                ImageView birdImage = findViewById(R.id.birdImageFS);
                                String imageUri =  bird.getImageLink() ;
                                Picasso.get().load(imageUri).into(birdImage);

                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {

                            }
                        });
                queue.add(strObjRequest);
            }
            catch(Exception e1) {
                Log.d(TAG, e1.toString());

            }
        }
        catch (Exception e2) {
            Log.d(TAG, e2.toString());

        }
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

