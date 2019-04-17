 package com.example.nfckopernikscanner;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        city = getIntent().getStringExtra("city");
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng olsztyn = new LatLng(53.778, 20.49315);
        LatLng dobre = new LatLng(53.987, 20.3978);
        LatLng lidzbark = new LatLng(54.1259, 20.5831);
        LatLng pieniezno = new LatLng(54.2361, 20.1288);
        LatLng frombork = new LatLng(54.35666, 19.681);
        LatLng torun = new LatLng(53.01, 18.6);
        if(city.equals("olsztyn")){
            mMap.addMarker(new MarkerOptions().position(olsztyn).title("Olsztyn!"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(olsztyn, 10.0f));
        }
        if(city.equals("dobre")){
            mMap.addMarker(new MarkerOptions().position(dobre).title("Dobre Miasto!"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(dobre, 12.0f));
        }
        if(city.equals("lidzbark")){
            mMap.addMarker(new MarkerOptions().position(lidzbark).title("Lidzbark Warmiński!"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lidzbark, 10.0f));
        }
        if(city.equals("pieniezno")){
            mMap.addMarker(new MarkerOptions().position(pieniezno).title("Pieniężno!"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pieniezno, 14.5f));
        }
        if(city.equals("frombork")){
            mMap.addMarker(new MarkerOptions().position(frombork).title("Frombork!"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(frombork, 10.0f));
        }
        if(city.equals("torun")){
            mMap.addMarker(new MarkerOptions().position(torun).title("Toruń!"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(torun, 10.0f));
        }
    }
}
