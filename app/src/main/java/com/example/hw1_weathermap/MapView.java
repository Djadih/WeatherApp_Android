package com.example.hw1_weathermap;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapView extends AppCompatActivity implements OnMapReadyCallback {
    //Start Hussam driving
    private double lat;
    private double lng;
    private static final String THE_JSON = "the_json";
    private String address;
    //end Hussam Driving
    //Start Alex driving
    private String darkSky;
    private String temperature;
    private String humidity;
    private String windSpeed;
    private String precipitation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        address = getIntent().getStringExtra(THE_JSON);
        //End Alex driving Start Hussam driving
        // Retrieve the content view that renders the map.
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getLat(address);
        getLong(address);
        Toast(lat+"");
        Toast(lng+"");


        darkSky(lat, lng);
        //end hussam driving
    }

    private void Toast(String toast){//Alex made this method
        Toast myToast = Toast.makeText(this, toast, Toast.LENGTH_SHORT);
        myToast.show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {//Hussam made this method
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        //Toast("function is called");
        //LatLng location = new LatLng(37.4239752, -122.0792527);
        LatLng location = new LatLng(lat, lng);
        googleMap.addMarker(new MarkerOptions().position(location)
                .title("You are here."));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));


        darkSky(lat, lng);

        if (darkSky != null)
            Toast("darksky is not null");



    }


    private double getLat(String address){//Alex drove for this method

        int latIndex = address.indexOf("\"lat\" : ");
        //String debug = address.substring(latIndex+8, latIndex+8+7);

        this.lat = Double.parseDouble(address.substring(latIndex+8, latIndex+8+7));

        //int latIndex = address.indexOf("\"lat\" : "), 5));

        return lat;
    }


    private double getLong(String address){//Alex Drove for this method

        int lngIndex = address.indexOf("\"lng\" : ");
        //String debug = address.substring(latIndex+8, latIndex+8+7);

        this.lng = Double.parseDouble(address.substring(lngIndex+8, lngIndex+8+7));

        return lng;
    }



    public void darkSky(double lat_coord, double long_coord){//Hussam drove for this method

// ...

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.darksky.net/forecast/a346553289d9d619e9edadca082f8ce1/"+lat_coord+","+long_coord+"\n";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //textView.setText("Response is: "+ response.substring(0,500));
                        darkSky = response;
                        parseSky(darkSky);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }


    private void parseSky(String darkSky){//Alex starts driving

        final TextView tempText = findViewById(R.id.temp);
        final TextView humidityText = findViewById(R.id.humidity);
        final TextView windSpeedText = findViewById(R.id.windSpeed);
        final TextView precip = findViewById(R.id.precip);
        //End Alex Driving
        //Start Hussam Driving
        //Toast("hello"+darkSky);
        temperature = darkSky.substring(darkSky.indexOf("temperature")+"temperature".length()+2, darkSky.indexOf("temperature")+"temperature".length()+2+4);
        tempText.setText("Current temperature: "+temperature+"F");

        humidity = darkSky.substring(darkSky.indexOf("humidity")+"humidity".length()+2, darkSky.indexOf("humidity")+"humidity".length()+2+4);
        humidityText.setText("Current humidity: "+humidity+"%");

        windSpeed = darkSky.substring(darkSky.indexOf("windSpeed")+"windSpeed".length()+2, darkSky.indexOf("windSpeed")+"windSpeed".length()+2+4);
        windSpeedText.setText("Current wind speed: "+windSpeed+"mph");

        precipitation = darkSky.substring(darkSky.indexOf("precipProbability")+"precipProbability".length()+2, darkSky.indexOf("precipProbability")+"precipProbability".length()+2+1);
        precip.setText("Chance of Rain: "+precipitation+"%");

    }//End Hussam driving

//    private String getCity(String address){
//        String city;
//
//        city = address.substring(address.indexOf())
//
//        return city;
//    }

}