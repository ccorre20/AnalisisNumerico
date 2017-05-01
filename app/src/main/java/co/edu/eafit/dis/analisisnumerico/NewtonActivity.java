package co.edu.eafit.dis.analisisnumerico;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothDevice;
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

public class NewtonActivity extends AppCompatActivity {

    SimpleBluetooth simpleBluetooth;
    private static final int SCAN_REQUEST = 119;
    private static final int CHOOSE_SERVER_REQUEST = 120;
    String curMacAddress;

    EditText expText, exp2Text, iterText, tolText, iniValText, resultText;

    Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bisection);
        expText = (EditText)findViewById(R.id.expText);
        exp2Text = (EditText)findViewById(R.id.exp2Text);
        iterText = (EditText)findViewById(R.id.iterText);
        tolText = (EditText)findViewById(R.id.tolText);
        iniValText = (EditText)findViewById(R.id.iniValText);
        resultText = (EditText)findViewById(R.id.resultText);

        sendBtn = (Button)findViewById(R.id.sendBtn);
    }

    @Override
    protected void onStart() {
        super.onStart();
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
                Toast.makeText(NewtonActivity.this, "Conectado", Toast.LENGTH_SHORT).show();
                NewtonActivity.this.sendBtn.setEnabled(true);
            }

            @Override
            public void onBluetoothDataReceived(byte[] bytes, String data) {
                NewtonActivity.this.resultText.setText(data, TextView.BufferType.EDITABLE);
            }
        });
        simpleBluetooth.initializeSimpleBluetooth();
    }

    public void onSearchClick(View view){
        simpleBluetooth.scan(SCAN_REQUEST);
    }

    public void onSendClick(View view){
        String msg = "Newton" + ";" + expText.getText().toString() + ";" + exp2Text.getText().toString() +
                ";" + iterText.getText().toString() + ";" + tolText.getText().toString() +
                ";" + iniValText.getText().toString();
        simpleBluetooth.sendData(msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == SCAN_REQUEST){
            if(resultCode == RESULT_OK){
                curMacAddress = data.getStringExtra(DeviceDialog.DEVICE_DIALOG_DEVICE_ADDRESS_EXTRA);
                simpleBluetooth.connectToBluetoothServer(curMacAddress);
            }
        }
    }
}
