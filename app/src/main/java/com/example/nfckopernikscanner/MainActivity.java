package com.example.nfckopernikscanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.widget.Toast;

//ToDo: Miasta w ktorych mieszkal kopernik: Torun, Krakow, Padwa, Olsztyn, Lidzbark Warminski, Frombork
//ToDo: Wysylanie i odbieranie sygnalow z LEGO Mindstorms EV3

public class MainActivity extends AppCompatActivity {
    private NfcAdapter nfcAdapter;
    byte[] kartaMiejska = {91, 66, 64, 99, 54, 101, 101, 48, 51, 57};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        String action = intent.getAction();

        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            if (tag != null) {
                byte[] tagId = tag.getId();
                if (tagId == kartaMiejska) {
                    toTorun();
                    Toast.makeText(this,
                            "działa!!!!!",
                            Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(this,
                            tagId.toString(),
                            Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }

    }

    public void toTorun() {
        Intent myIntent = new Intent(MainActivity.this, cTorun.class);
        //myIntent.putExtra("key", value); //Optional parameters
        MainActivity.this.startActivity(myIntent);
    }
    //ToDo: dodaj reszte activities


    public void btnClicked(View view) {
        toTorun();
    }
}
