package com.example.marcelo.a05_09;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    BluetoothAdapter mBluetoothAdapter = null;
    BluetoothDevice mDevice = null;
    BluetoothSocket mSocket = null;
    static final int AcionaBT = 0;
    static final int SOLICITA_CONEXAO = 1;
    static final int BT_VISIVEL = 2;
    static final int LISTACLICK = 3;
    private static String ENDERECOMAC = null;
    boolean Conectar = false;
    boolean Sair = false;
    Button btnConectar;
    Button btnSair;

    UUID mUUID = UUID.fromString("00000000-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBluetoothAdapter == null) {
            //sem bluetooth
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent ACIONABT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(ACIONABT, AcionaBT);

        }
        Intent disooverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        disooverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivityForResult(disooverableIntent, BT_VISIVEL);


        btnConectar = (Button) findViewById(R.id.btnConectar);

        BluetoothAdapter mBluetothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetothAdapter.isDiscovering()) {
            mBluetothAdapter.cancelDiscovery();
        } else {
            mBluetothAdapter.startDiscovery();
        }

        BroadcastReceiver discoveryResult = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                BluetoothDevice remoteDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            }
        };


        btnConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Conectar) {
                    //desconectar
                } else {
                    //conectar
                    Intent abreLista = new Intent(MainActivity.this, ListaDispositivos.class);
                    startActivityForResult(abreLista, SOLICITA_CONEXAO);


                }

            }


            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                switch (requestCode) {

                    case AcionaBT:
                        if (resultCode == Activity.RESULT_OK) {
                            Toast.makeText(getApplicationContext(), "Bluetooth ativado", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "O app será finalizado", Toast.LENGTH_LONG).show();
                            finish();
                        }
                        break;


                    }
                }

                });


        }

    };



















