package com.example.irishbirdapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;


import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;

import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.example.irishbirdapp.*;


import org.json.JSONArray;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String RANDOM_BIRD_URI = "https://ead2ca2birdappapi20210409142733.azurewebsites.net/api/birds/randomBird";
    private String SEARCH_URI = "https://ead2ca2birdappapi20210409142733.azurewebsites.net/api/birds/search?searchTerm=";
    private static final String TAG = "Irish Bird App";
    public final static Bird REVIEW_MESSAGE=  new Bird();
    Button searchButton;
    EditText searchText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchButton = (Button)findViewById(R.id.searchButton);

    }

    public void searchForABird(View view)
    {
        final TextView outputBirds = (TextView) findViewById(R.id.outputTextView);
                searchText = (EditText)findViewById(R.id.searchTextBox);
                String searchTerm = searchText.getText().toString();
                String searchUrl = SEARCH_URI+searchTerm;

                try{
                    //making a string request
                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                    Log.d(TAG, "Making request");
                    Log.d(TAG, searchTerm);
                    Log.d(TAG, searchUrl);
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









/*
    private void doMySearch(String query) {
        SearchView searchView = findViewById(R.id.searchView3);
        String input = (String) searchView.getQuery();
        Log.d("Search input ", input);

    }


    public void displayMessage(View view) {
        SearchView searchView = findViewById(R.id.searchView3);
        String input = (String) searchView.getQuery();
        Log.d("Search input ", input);
    }
*/
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
}