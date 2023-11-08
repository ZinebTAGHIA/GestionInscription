package ma.zineb.school;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class EditFiliere extends AppCompatActivity {

    private EditText code;
    private EditText name;
    private Button bnSave;
    RequestQueue requestQueue;

    private String editUrl = "http://192.168.1.3:8082/api/filieres/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_filiere);

        code = findViewById(R.id.code);
        name = findViewById(R.id.libelle);
        bnSave = findViewById(R.id.bnSave);

        int id = getIntent().getIntExtra("id", 0);
        String codeValue = getIntent().getStringExtra("code").toString();
        String nameValue = getIntent().getStringExtra("name").toString();

        code.setText(codeValue);
        name.setText(nameValue);
        bnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("code", code.getText().toString());
                    jsonBody.put("name", name.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT,
                        editUrl+id, jsonBody, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("resultat", response + "");
                        Toast.makeText(getApplicationContext(),"Filière modifiée avec succès", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Erreur", error.toString());
                    }
                });
                requestQueue.add(request);

            }
        });

    }
}