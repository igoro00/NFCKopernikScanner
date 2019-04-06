package com.example.nfckopernikscanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class cDobre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_dobre);
    }


    public void toLidzbark() {
        Intent myIntent = new Intent(cDobre.this, cLidzbark.class);
        //myIntent.putExtra("key", value); //Optional parameters
        cDobre.this.startActivity(myIntent);
    }


    public void btnClicked(View view) {
        toLidzbark();
    }
}
