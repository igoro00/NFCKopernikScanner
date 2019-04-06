package com.example.nfckopernikscanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;



public class cPieniezno extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_pieniezno);
    }

    public void toFrombork() {
        Intent myIntent = new Intent(cPieniezno.this, cFrombork.class);
        //myIntent.putExtra("key", value); //Optional parameters
        cPieniezno.this.startActivity(myIntent);
    }


    public void btnClicked(View view) {
        toFrombork();
    }
}
