package com.example.nfckopernikscanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

//ToDo: Miasta w ktorych mieszkal kopernik: Torun, Krakow, Padwa, Olsztyn, Lidzbark Warminski, Frombork
//ToDo: Wysylanie i odbieranie sygnalow z LEGO Mindstorms EV3

public class MainActivity extends AppCompatActivity {
    Handler bluetoothIn;
    private NfcAdapter nfcAdapter;
    byte[] kartaMiejska = {91, 66, 64, 99, 54, 101, 101, 48, 51, 57};
    final int handlerState = 0;                        //used to identify handler message
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private StringBuilder recDataString = new StringBuilder();

    private com.example.nfckopernikscanner.MainActivity.ConnectedThread mConnectedThread;
    // SPP UUID service - this should work for most devices
    //private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final UUID BTMODULEUUID = UUID.fromString("1197d03e-69f9-44a1-b47c-2138001b8e1d");

    // String for MAC address
    private static String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toList();
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
        btAdapter = BluetoothAdapter.getDefaultAdapter();       // get Bluetooth adapter
        checkBTState();
    }
    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {

        return  device.createRfcommSocketToServiceRecord(BTMODULEUUID);
        //creates secure outgoing connecetion with BT device using UUID
    }


    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        //Get the MAC address from the DeviceListActivty via EXTRA
        address = intent.getStringExtra(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
        Toast.makeText(getBaseContext(),intent.getStringExtra(DeviceListActivity.EXTRA_DEVICE_ADDRESS) , Toast.LENGTH_LONG).show();
        //create device and set the MAC address
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        try {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "Socket creation failed", Toast.LENGTH_LONG).show();
        }
        // Establish the Bluetooth socket connection.
        try
        {
            Log.d("Socket", "tries to connect");
            btSocket.connect();
        } catch (IOException e) {
            try
            {
                Log.d("Socket", "Socket closed" + e);
                btSocket.close();
            } catch (IOException e2)
            {
                Log.d("Socket", "Socket cant be closed" + e2);
            }
        }
        mConnectedThread = new com.example.nfckopernikscanner.MainActivity.ConnectedThread(btSocket);
        mConnectedThread.start();

        //I send a character when resuming.beginning transmission to check device is connected
        //If it is not an exception will be thrown in the write method and finish() will be called
        mConnectedThread.write("x");
        /*String action = intent.getAction();

        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            if (tag != null) {
                byte[] tagId = tag.getId();
                if (tagId == kartaMiejska) {
                    toTorun();
                    mConnectedThread.write("0");    // Send "0" via Bluetooth
                    Toast.makeText(getBaseContext(), "Działa!!!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this,
                            tagId.toString(),
                            Toast.LENGTH_LONG).show();
                }
            }
        } */

    }

    public void toTorun() {
        Intent myIntent = new Intent(MainActivity.this, cTorun.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void toKrakow() {
        Intent myIntent = new Intent(MainActivity.this, cKrakow.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void toPadwa() {
        Intent myIntent = new Intent(MainActivity.this, cPadwa.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void toOlsztyn() {
        Intent myIntent = new Intent(MainActivity.this, cOlsztyn.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void toLidzbark() {
        Intent myIntent = new Intent(MainActivity.this, cLidzbark.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void toFrombork() {
        Intent myIntent = new Intent(MainActivity.this, cFrombork.class);
        MainActivity.this.startActivity(myIntent);
    }
    public void toList() {
        Intent myIntent = new Intent(MainActivity.this, DeviceListActivity.class);
        MainActivity.this.startActivity(myIntent);
    }
    public void btnClicked(View view) {
        toTorun();
    }
    public void toConnect(View view) {
        toList();
    }

    //Checks that the Android device Bluetooth is available and prompts to be turned on if off
    private void checkBTState() {
        if(btAdapter==null) {
            Toast.makeText(getBaseContext(), "Device does not support bluetooth", Toast.LENGTH_LONG).show();
        } else {
            if (btAdapter.isEnabled()) {
            } else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }

    //create new class for connect thread
    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        //creation of the connect thread
        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                //Create I/O streams for connection
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[256];
            int bytes;

            // Keep looping to listen for received messages
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);            //read bytes from input buffer
                    String readMessage = new String(buffer, 0, bytes);
                    // Send the obtained bytes to the UI Activity via handler
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }
        //write method
        public void write(String input) {
            byte[] msgBuffer = input.getBytes();           //converts entered String into bytes
            try {
                mmOutStream.write(msgBuffer);                //write bytes over BT connection via outstream
            } catch (IOException e) {
                //if you cannot write, close the application
                Toast.makeText(getBaseContext(), "Connection Failure  "+e, Toast.LENGTH_LONG).show();
                finish();

            }
        }
    }
}