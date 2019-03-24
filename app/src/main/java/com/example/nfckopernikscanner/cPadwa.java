package com.example.nfckopernikscanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class cPadwa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_padwa);
    }


    public void toOlsztyn() {
        Intent myIntent = new Intent(cPadwa.this, cOlsztyn.class);
        //myIntent.putExtra("key", value); //Optional parameters
        cPadwa.this.startActivity(myIntent);
    }


    public void btnClicked(View view) {
        toOlsztyn();
    }
}
