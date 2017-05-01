package co.edu.eafit.dis.analisisnumerico;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothDevice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.devpaul.bluetoothutillib.SimpleBluetooth;
import com.devpaul.bluetoothutillib.utils.SimpleBluetoothListener;

public class SlaveActivity extends AppCompatActivity {

    SimpleBluetooth simpleBluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slave);
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
                Toast.makeText(SlaveActivity.this, "Conectado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBluetoothDataReceived(byte[] bytes, String data) {
                String[] vals = data.split(";");
                SlaveActivity.this.ProcessData(vals);
            }
        });
        simpleBluetooth.initializeSimpleBluetooth();
        simpleBluetooth.createBluetoothServerConnection();
    }

    public void onVisibleClick(View view){
        simpleBluetooth.makeDiscoverable(300);
    }

    public void ProcessData(String[] data){
        String ans="";
        switch(data[0]) {
            case "IncrementalSearch":
                ans = IncrementalSearchUtil.IncrementalSearch(data[1], Integer.parseInt(data[2]),
                        Double.parseDouble(data[3]),Double.parseDouble(data[4]));
                break;
            case "Bisection":
                ans = BisectionUtil.Bisection(data[1], Integer.parseInt(data[2]),
                        Double.parseDouble(data[3]), Double.parseDouble(data[4]),
                        Double.parseDouble(data[5]));
                break;
            case "FalseRule":
                ans = FalseRuleUtil.FalseRule(data[1],Integer.parseInt(data[2]),
                        Double.parseDouble(data[3]), Double.parseDouble(data[4]),
                        Double.parseDouble(data[5]));
                break;
            case "FixedPoint":
                ans = FixedPointUtil.FixedPoint(data[1], data[2], Integer.parseInt(data[3]),
                         Double.parseDouble(data[4]), Double.parseDouble(data[5]));
                break;
            case "Newton":
                ans = NewtonMethodUtil.NewtonMethod(data[1], data[2], Integer.parseInt(data[3]),
                        Double.parseDouble(data[4]), Double.parseDouble(data[5]));
                break;
            case "Secant":
                // expText, iterText, tolText, infText, supText
                ans = SecantMethodUtil.SecantMethod(data[1], Integer.parseInt(data[2]),
                        Double.parseDouble(data[3]), Double.parseDouble(data[4]),
                        Double.parseDouble(data[5]));
                break;
            case "MultipleRoots":
                ans = MultipleRootsUtil.MultipleRoots(data[1], data[2], data[3],Integer.parseInt(data[4]),
                        Double.parseDouble(data[5]), Double.parseDouble(data[6]));
                break;

        }
        simpleBluetooth.sendData(ans);
    }
}
