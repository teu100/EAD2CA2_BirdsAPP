package com.example.irishbirdapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import android.widget.TextView;
import android.util.Log;
import com.google.gson.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;


import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String SERVICE_URI = "https://ead2ca2birdappapi20210409142733.azurewebsites.net/api/birds";          // or https
    private static final String TAG = "Irish Bird App";
    public final static Bird REVIEW_MESSAGE=  new Bird();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public void displayMessage(View view) {
        Log.d(TAG, "Random button has beeen cliked");
        //Snackbar.make
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

        //getting a textView to display the result
        final TextView outputBirds = (TextView) findViewById(R.id.outputTextView);

        try{
            //making a string request
            RequestQueue queue = Volley.newRequestQueue(this);
            Log.d(TAG, "Making request");
            try{
                StringRequest strObjRequest = new StringRequest(Request.Method.GET, SERVICE_URI,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response)
                            {
                                // parse resulting string containing JSON to Greeting object
                                Bird[] birds1 = new Gson().fromJson(response, Bird[].class);
                                Random randomGen = new Random();
                                int randomNumber = randomGen.nextInt(19);
                                outputBirds.setText(birds1[0].toString());
                                Log.d(TAG, "Displaying data : " + birds1[0].toString());
                                birdInfo(birds1[0]);
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                outputBirds.setText(error.toString());
                                Log.d(TAG, "Error" + error.toString());
                            }
                        });
                queue.add(strObjRequest);           // can have multiple in a queue, and can cancel
            }
            catch(Exception e1) {
                Log.d(TAG, e1.toString());
                outputBirds.setText(e1.toString());
            }
        }
        catch (Exception e2) {
            Log.d(TAG, e2.toString());
            outputBirds.setText(e2.toString());
        }
    }
}