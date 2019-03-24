package com.example.nfckopernikscanner;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.widget.Toast;



//ToDo: Miasta w ktorych mieszkal kopernik: Torun, Krakow, Padwa, Olsztyn, Lidzbark Warminski, Frombork
//ToDo: Wysylanie i odbieranie sygnalow z LEGO Mindstorms EV3

public class MainActivity extends AppCompatActivity {
    private NfcAdapter nfcAdapter;
    private MainViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // This method return false if there is an error, so if it does, we should close.
        if (!viewModel.setupViewModel(getIntent().getStringExtra("device_name"), getIntent().getStringExtra("device_mac"))) {
            Log.d("bt", "setup failed");
            finish();
            return;
        }
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            Toast.makeText(this,
                    "NFC NOT supported on this devices!",
                    Toast.LENGTH_LONG).show();
            finish();
        } else if (!nfcAdapter.isEnabled()) {
            Toast.makeText(this,
                    "NFC NOT Enabled!",
                    Toast.LENGTH_LONG).show();
            finish();
        }
        viewModel.connect();

    }

    public void btnClicked(View view) {
        Intent myIntent = new Intent(MainActivity.this, cTorun.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void sendData(String data) {
        viewModel.sendMessage(data);
    }

}