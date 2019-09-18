package com.example.hw1_weathermap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    //Alex was driving for these declarations
    private static final String THE_JSON = "the_json";
    private String JSON;
    private Intent mapIntent;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void submitText(View view){//Alex was driving during th creation of this method

        TextInputEditText submittedAddress = (TextInputEditText) findViewById(R.id.textInputEditText);

        String textInput = submittedAddress.getText().toString();
        //String textInput = "1600 Amphitheatre Pkwy, Mountain View, CA 94043, USA";
        String address = formatAddress(textInput);

        //toastAddress(address);

        //setContentView(R.layout.activity_map_view);

        // Create an Intent to start the second activity

        //Intent mapIntent = new Intent(this, MapView.class);
        toMap(address);
        //toastAddress(JSON);

    }
    //Hussam was driving for this method
    private String formatAddress(String address){
        address = address.replace(' ', '+');

        address = address.replaceAll("[^a-zA-Z0-9,+]", "");

        //toastAddress(address);
        return address;
    }
    //End hussam driving Alex Starts driving
    private void toastAddress(String address){
        Toast myToast = Toast.makeText(this, "Hopefully the weather's nice in: "+address,
                Toast.LENGTH_SHORT);
        myToast.show();
    }

    //Alex was driving for this method
    private void toMap(String address) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=AIzaSyAjyM0A4BiPtqT_n5_v48uHEd7rltaIp8U";
        //String url ="https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=AIzaSyAjyM0A4BiPtqT_n5_v48uHEd7rltaIp8U";

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    //Hussam was driving for this method
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {//End Alex driving Hussam Starts driving
                        // Display the first 500 characters of the response string.
                        //  textView.setText("Response is: "+ response.substring(0,500));
                        //toastAddress(response);
                        JSON = response;
                        mapIntent = new Intent(getApplicationContext(), MapView.class);
                        mapIntent.putExtra(THE_JSON, JSON);
                        // Start the new activity.
                        startActivity(mapIntent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //toastAddress(error.toString());
                //textView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }


}