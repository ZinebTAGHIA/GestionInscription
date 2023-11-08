package ma.zineb.school;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.zineb.school.adapter.EtudiantAdapter;
import ma.zineb.school.beans.Etudiant;

public class ListEtudiants extends AppCompatActivity {

    private ListView listView;
    private List<Etudiant> etudiantList;
    private EtudiantAdapter adapter;

    private Button btnAdd;
    private String url = "http://192.168.1.3:8082/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_etudiant);
        listView = findViewById(R.id.listView);
        retrieveStudentsData();

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListEtudiants.this, AddStudent.class);
                startActivity(intent);
            }
        });
    }

    private void retrieveStudentsData() {
        String loadUrl = this.url + "etudiants";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, loadUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Parse the JSON response and display data in ListView
                        Log.d("response", response+"");
                        handleJsonResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListEtudiants.this, "Error fetching data", Toast.LENGTH_SHORT).show();

                        Log.d("err", error+"");
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void handleJsonResponse(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            etudiantList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Etudiant etudiant = new Etudiant(
                        jsonObject.getInt("id"),
                        jsonObject.getString("code"),
                        jsonObject.getString("name"),
                        "hjh",
                        "hjh",
                        "hbb",
                        "vb",
                        1
                );
                etudiantList.add(etudiant);
            }

            adapter = new EtudiantAdapter(this, etudiantList);
            listView.setAdapter((ListAdapter) adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}