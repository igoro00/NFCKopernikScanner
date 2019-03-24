package com.example.nfckopernikscanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class cLidzbark extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_lidzbark);
    }
    public void toFrombork() {
        Intent myIntent = new Intent(cLidzbark.this, cFrombork.class);
        //myIntent.putExtra("key", value); //Optional parameters
        cLidzbark.this.startActivity(myIntent);
    }


    public void btnClicked(View view) {
        toFrombork();
    }
}
