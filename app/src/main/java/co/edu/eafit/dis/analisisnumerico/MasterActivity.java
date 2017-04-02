package co.edu.eafit.dis.analisisnumerico;

import android.app.AlertDialog;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.devpaul.bluetoothutillib.SimpleBluetooth;
import com.devpaul.bluetoothutillib.dialogs.DeviceDialog;
import com.devpaul.bluetoothutillib.utils.SimpleBluetoothListener;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class MasterActivity extends AppCompatActivity {

    /*
    BluetoothAdapter mBluetoothAdapter;
    int REQUEST_ENABLE_BT;
    */
    SimpleBluetooth simpleBluetooth;
    private static final int SCAN_REQUEST = 119;
    private static final int CHOOSE_SERVER_REQUEST = 120;
    String curMacAddress;

    EditText exprText, iterText, tolText, infText, supText, resultText;
    Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        /*
        REQUEST_ENABLE_BT = 1;
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        */
        exprText = (EditText)findViewById(R.id.exprText);
        iterText = (EditText)findViewById(R.id.iterText);
        tolText = (EditText)findViewById(R.id.tolText);
        infText = (EditText)findViewById(R.id.infText);
        supText = (EditText)findViewById(R.id.supText);
        resultText = (EditText)findViewById(R.id.resultText);

        sendBtn = (Button)findViewById(R.id.sendBtn);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*
        if(mBluetoothAdapter != null){
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
        */
        simpleBluetooth = new SimpleBluetooth(this, new SimpleBluetoothListener() {
            @Override
            public void onBluetoothA2DPRequested(BluetoothA2dp bluetoothA2dp) {

            }

            @Override
            public void onDiscoveryStarted() {

            }

            @Override
            public void onDiscoveryFinished() {

            }

            @Override
            public void onDevicePaired(BluetoothDevice device) {

            }

            @Override
            public void onDeviceUnpaired(BluetoothDevice device) {

            }

            @Override
            public void onDeviceDisconnected(BluetoothDevice device) {

            }

            @Override
            public void onDeviceConnected(BluetoothDevice device) {
                Toast.makeText(MasterActivity.this, "Conectado", Toast.LENGTH_SHORT).show();
                MasterActivity.this.sendBtn.setEnabled(true);
            }

            @Override
            public void onBluetoothDataReceived(byte[] bytes, String data) {
                MasterActivity.this.resultText.setText(data, TextView.BufferType.EDITABLE);
            }
        });
        simpleBluetooth.initializeSimpleBluetooth();
    }

    public void onSearchClick(View view){
        /*
        Intent discoverableIntent =
                new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);
        */
        simpleBluetooth.scan(SCAN_REQUEST);
    }

    public void onSendClick(View view){
        String msg = exprText.getText().toString() + ";" + iterText.getText().toString() +
                ";" + tolText.getText().toString() + ";" + infText.getText().toString() +
                ";" + supText.getText().toString();
        simpleBluetooth.sendData(msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*
        if(requestCode == REQUEST_ENABLE_BT){
            if(resultCode != RESULT_OK){
                AlertDialog alertDialog = new AlertDialog.Builder(MasterActivity.this).create();
                alertDialog.setTitle("Alerta");
                alertDialog.setMessage("Algo fallo con el Bluetooth");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        }
        */
        if(requestCode == SCAN_REQUEST){
            if(resultCode == RESULT_OK){
                curMacAddress = data.getStringExtra(DeviceDialog.DEVICE_DIALOG_DEVICE_ADDRESS_EXTRA);
                simpleBluetooth.connectToBluetoothDevice(curMacAddress);
            }
        }
    }
}
