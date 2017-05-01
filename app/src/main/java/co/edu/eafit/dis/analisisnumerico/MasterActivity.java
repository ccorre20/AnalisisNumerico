package co.edu.eafit.dis.analisisnumerico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.Toast;

public class MasterActivity extends AppCompatActivity {


    private static ListView list_view;
    private static String[] METHODS = new String[] {
            "Incremental Search", "Bisection", "False Rule",
            "Fixed Point", "Newton", "Secant", "MultipleRoots"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        listView();
    }

    public void listView(){
        list_view = (ListView)findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.method_list,METHODS);
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String value = (String)list_view.getItemAtPosition(position);
                        switch(value){
                            case "Incremental Search":
                                break;
                            case "Bisection":
                                Intent intent = new Intent(MasterActivity.this,BisectionActivity.class);
                                startActivity(intent);
                                break;
                        }

                    }
                }
        );
    }

}
