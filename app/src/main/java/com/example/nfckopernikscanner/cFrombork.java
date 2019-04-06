package com.example.nfckopernikscanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class cFrombork extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_frombork);
    }
    public void toTorun() {
        Intent myIntent = new Intent(this, cTorun .class);
        //myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }


    public void btnClicked(View view) {
        toTorun();
    }
}
