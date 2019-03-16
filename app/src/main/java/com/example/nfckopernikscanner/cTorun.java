package com.example.nfckopernikscanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class cTorun extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_torun);
    }
    public void toFrombork() {
        Intent myIntent = new Intent(cTorun.this, cKrakow.class);
        //myIntent.putExtra("key", value); //Optional parameters
        cTorun.this.startActivity(myIntent);
    }


    public void btnClicked(View view) {
        toFrombork();
    }
}