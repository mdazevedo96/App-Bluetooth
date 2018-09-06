package com.example.marcelo.a05_09;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class ListaDispositivos extends ListActivity {

    private BluetoothAdapter mBluetoothAdapter2 = null;
    static String EndMAC = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> ArrayBluetooth = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);

        mBluetoothAdapter2 = BluetoothAdapter.getDefaultAdapter();

        Set<BluetoothDevice> dispositivosPareados = mBluetoothAdapter2.getBondedDevices();

        if (dispositivosPareados.size() > 0) {
            for (BluetoothDevice dispositivos : dispositivosPareados) {
                String nomeBT = dispositivos.getName();
                String macBT = dispositivos.getAddress();
                ArrayBluetooth.add(nomeBT + "\n" + macBT);
            }
        }
        setListAdapter(ArrayBluetooth);


    }
    protected void onListItemClick (ListView l, View v, int position, long id) {
        super.onListItemClick(l,v,position,id);

        String infoGeral = ((TextView) v).getText().toString();
        String enderecoMAC = infoGeral.substring(infoGeral.length()-17);

        Toast.makeText(getApplicationContext(),"Info:" + infoGeral,Toast.LENGTH_LONG).show();

        Intent retornaMAC = new Intent();
        retornaMAC.putExtra(EndMAC,enderecoMAC);
        setResult(RESULT_OK,retornaMAC);
        finish();
    }
}
