package co.edu.eafit.dis.analisisnumerico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class InitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
    }

    public void onMasterClick(View view){
        startActivity(new Intent(this, BisectionActivity.class));
    }

    public void onSlaveClick(View view){
        startActivity(new Intent(this, SlaveActivity.class));
    }

}
