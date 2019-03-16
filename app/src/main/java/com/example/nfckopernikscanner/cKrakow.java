package com.example.nfckopernikscanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class cKrakow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_krakow);
    }
    public void toPadwa() {
        Intent myIntent = new Intent(cKrakow.this, cPadwa.class);
        cKrakow.this.startActivity(myIntent);
    }


    public void btnClicked(View view) {
        toPadwa();
    }
}
