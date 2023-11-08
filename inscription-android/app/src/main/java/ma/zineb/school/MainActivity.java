package ma.zineb.school;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ImageButton filieresBtn;
    private ImageButton rolesBtn;
    private ImageButton studentsBtn;

    RequestQueue requestQueue;
    String insertUrl = "http://192.168.1.3:8082/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filieresBtn = (ImageButton) findViewById(R.id.filieres);
        rolesBtn = (ImageButton) findViewById(R.id.roles);
        studentsBtn = (ImageButton) findViewById(R.id.students);

        filieresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ok", "ok");
                Intent intent = new Intent(MainActivity.this, ListFilieres.class);
                startActivity(intent);
            }
        });
        rolesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ok", "ok");
                Intent intent = new Intent(MainActivity.this, ListRoles.class);
                startActivity(intent);
            }
        });
        studentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ok", "ok");
                Intent intent = new Intent(MainActivity.this, ListEtudiants.class);
                startActivity(intent);
            }
        });
    }
}