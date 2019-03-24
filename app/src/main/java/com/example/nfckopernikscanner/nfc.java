package com.example.nfckopernikscanner;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class nfc extends AppCompatActivity {
    MainActivity mainActiv;
    private NfcAdapter nfcAdapter;
    byte[] kartaMiejska = {91, 66, 64, 99, 54, 101, 101, 48, 51, 57};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        mainActiv = new MainActivity();
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
                toTorun();
                if (tagId == kartaMiejska) {
                    Toast.makeText(getBaseContext(), "Działa!!!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this,
                            tagId.toString(),
                            Toast.LENGTH_LONG).show();
                    kartaMiejska = tagId;
                }
            }
        }
    }
    public void toTorun() {
        mainActiv.sendData("xd");
        Intent myIntent = new Intent(nfc.this, cTorun.class);
        nfc.this.startActivity(myIntent);
    }

    public void toKrakow() {
        Intent myIntent = new Intent(nfc.this, cKrakow.class);
        nfc.this.startActivity(myIntent);
    }

    public void toPadwa() {
        Intent myIntent = new Intent(nfc.this, cPadwa.class);
        nfc.this.startActivity(myIntent);
    }

    public void toOlsztyn() {
        Intent myIntent = new Intent(nfc.this, cOlsztyn.class);
        nfc.this.startActivity(myIntent);
    }

    public void toLidzbark() {
        Intent myIntent = new Intent(nfc.this, cLidzbark.class);
        nfc.this.startActivity(myIntent);
    }

    public void toFrombork() {
        Intent myIntent = new Intent(nfc.this, cFrombork.class);
        nfc.this.startActivity(myIntent);
    }
}
