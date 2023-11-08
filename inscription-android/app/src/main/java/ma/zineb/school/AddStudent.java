package ma.zineb.school;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.util.Collections;

import ma.zineb.school.adapter.FiliereAdapter;
import ma.zineb.school.beans.Filiere;
import ma.zineb.school.beans.Role;

public class AddStudent extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<Filiere> filiereList;
    String[] filieres;

    TextView roles;
    boolean[] selectedRole;
    ArrayList<Integer> roleIDList = new ArrayList<>();

    ArrayList<Role> roleList;
    String[] roleArray;
    private String url = "http://192.168.1.3:8082/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        String loadFilieresUrl = this.url + "filieres";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, loadFilieresUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response", response + "");
                        handleJsonResponseFiliere(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("err", error + "");
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        Spinner spin = findViewById(R.id.filieres);
        spin.setOnItemSelectedListener(this);

        String loadRolesUrl = this.url + "roles";

        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, loadRolesUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response", response + "");
                        handleJsonResponseRole(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("err", error + "");
                    }
                });

        RequestQueue requestQueue2 = Volley.newRequestQueue(this);
        requestQueue2.add(stringRequest2);

    }

    @Override
    public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView arg0) {
        // Auto-generated method stub
    }

    private void handleJsonResponseFiliere(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            filiereList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Filiere filiere = new Filiere(
                        jsonObject.getInt("id"),
                        jsonObject.getString("code"),
                        jsonObject.getString("name")
                );
                filiereList.add(filiere);
            }

            filieres = new String[filiereList.size()];

            for (int i = 0; i < filiereList.size(); i++) {
                filieres[i] = filiereList.get(i).getName();
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, filieres);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Spinner spinner = findViewById(R.id.filieres);
            spinner.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void handleJsonResponseRole(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            roleList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Role role = new Role(
                        jsonObject.getInt("id"),
                        jsonObject.getString("name")
                );
                roleList.add(role);
            }

            roleArray = new String[roleList.size()];

            for (int i = 0; i < roleList.size(); i++) {
                roleArray[i] = roleList.get(i).getName();
            }

            roles = findViewById(R.id.roles);

            selectedRole = new boolean[roleArray.length];

            roles.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(AddStudent.this);

                    builder.setTitle("Select Role");

                    builder.setCancelable(false);

                    builder.setMultiChoiceItems(roleArray, selectedRole, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            if (b) {
                                roleIDList.add(i);
                                Collections.sort(roleIDList);
                            } else {
                                roleIDList.remove(Integer.valueOf(i));
                            }
                        }
                    });

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int j = 0; j < roleIDList.size(); j++) {
                                stringBuilder.append(roleArray[roleIDList.get(j)]);
                                if (j != roleIDList.size() - 1) {
                                    stringBuilder.append(", ");
                                }
                            }
                            roles.setText(stringBuilder.toString());
                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            for (int j = 0; j < selectedRole.length; j++) {
                                selectedRole[j] = false;
                                roleIDList.clear();
                                roles.setText("");
                            }
                        }
                    });
                    builder.show();
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
