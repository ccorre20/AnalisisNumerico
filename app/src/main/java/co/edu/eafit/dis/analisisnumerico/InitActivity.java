package co.edu.eafit.dis.analisisnumerico;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.devpaul.bluetoothutillib.SimpleBluetooth;
import com.devpaul.bluetoothutillib.dialogs.DeviceDialog;
import com.devpaul.bluetoothutillib.utils.SimpleBluetoothListener;

public class InitActivity extends AppCompatActivity {

    private SimpleBluetooth simpleBluetooth;
    private static final int SCAN_REQUEST = 119;
    private static final int CHOOSE_SERVER_REQUEST = 120;
    //...other code....//
    private String curMacAddress;

    @Override
    protected void onResume() {
        super.onResume();
        simpleBluetooth = new SimpleBluetooth(this, new SimpleBluetoothListener() {

            @Override
            public void onBluetoothDataReceived(byte[] bytes, String data) {
                //read the data coming in.
            }

            @Override
            public void onDeviceConnected(BluetoothDevice device) {
                //a device is connected so you can now send stuff to it

            }

            @Override
            public void onDeviceDisconnected(BluetoothDevice device) {
                // device was disconnected so connect it again?
            }

            @Override
            public void onDiscoveryStarted() {

            }

            @Override
            public void onDiscoveryFinished() {

            }
        });
        simpleBluetooth.initializeSimpleBluetooth();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.scan) {
            simpleBluetooth.scan(SCAN_REQUEST);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SCAN_REQUEST || requestCode == CHOOSE_SERVER_REQUEST) {

            if(resultCode == RESULT_OK) {

                curMacAddress = data.getStringExtra(DeviceDialog.DEVICE_DIALOG_DEVICE_ADDRESS_EXTRA);
                if(requestCode == SCAN_REQUEST) {
                    simpleBluetooth.connectToBluetoothDevice(curMacAddress);
                } else {
                    simpleBluetooth.connectToBluetoothServer(curMacAddress);
                }

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        simpleBluetooth.endSimpleBluetooth();
    }
}
