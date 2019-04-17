package com.example.nfckopernikscanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class CityActivity extends AppCompatActivity {
    String city;
    TextView txt;
    TextView title;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        city = getIntent().getStringExtra("EXTRA_CITY");
        txt = (TextView)findViewById(R.id.textViewINFO);
        title = (TextView)findViewById(R.id.textViewCITY);
        img = (ImageView)findViewById(R.id.imageViewC);
        if(city.equals("olsztyn")){
            title.setText("OLSZTYN");
            txt.setText(R.string.olsztyn_info);
            img.setImageResource(R.drawable.c_olsztyn);
        }
        if(city.equals("dobre")){
            title.setText("DOBRE MIASTO");
            txt.setText(R.string.dobre_info);
            img.setImageResource(R.drawable.c_dobre);
        }
        if(city.equals("lidzbark")){
            title.setText("LIDZBARK WARMIŃSKI");
            txt.setText(R.string.lidzbark_info);
            img.setImageResource(R.drawable.c_lidzbark);
        }
        if(city.equals("pieniezno")){
            title.setText("PIENIĘŻNO");
            txt.setText(R.string.pieniezno_info);
            img.setImageResource(R.drawable.c_pieniezno);
        }
        if(city.equals("frombork")){
            title.setText("FROMBORK");
            txt.setText(R.string.frombork_info);
            img.setImageResource(R.drawable.c_frombork);
        }
        if(city.equals("torun")){
            title.setText("TORUŃ");
            txt.setText(R.string.torun_info);
            img.setImageResource(R.drawable.c_torun);
        }
    }


    public void btnClicked(View view) {
        Intent myIntent = new Intent(this, MapsActivity.class);
        myIntent.putExtra("city", city);
        startActivity(myIntent);
    }
}
