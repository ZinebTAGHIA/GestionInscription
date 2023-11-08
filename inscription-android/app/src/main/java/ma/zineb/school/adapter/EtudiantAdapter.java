package ma.zineb.school.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import ma.zineb.school.AddStudent;
import ma.zineb.school.EditStudent;
import ma.zineb.school.EditStudent;
import ma.zineb.school.ListEtudiants;
import ma.zineb.school.R;
import ma.zineb.school.beans.Etudiant;

public class EtudiantAdapter extends ArrayAdapter<Etudiant> {
    private List<Etudiant> etudiantList;
    private Context context;

    private String url = "http://192.168.1.3:8082/api/etudiants/";

    public EtudiantAdapter(Context context, List<Etudiant> etudiantList) {
        super(context, 0, etudiantList);
        this.etudiantList = etudiantList;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Etudiant etudiant = etudiantList.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_etudiant_item_layout, parent, false);
        }

        TextView id = convertView.findViewById(R.id.etudiantID);
        TextView name = convertView.findViewById(R.id.etudiantName);
        TextView code = convertView.findViewById(R.id.etudiantCode);

        id.setText(etudiant.getId()+"");
        name.setText(etudiant.getFirstName());
        code.setText(etudiant.getLastName());

        ImageButton buttonEdit = convertView.findViewById(R.id.buttonEdit);
        ImageButton buttonDelete = convertView.findViewById(R.id.buttonDelete);


        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditStudent.class);
                intent.putExtra("id", etudiant.getId());
                intent.putExtra("code", etudiant.getFirstName());
                intent.putExtra("name", etudiant.getLastName());
                context.startActivity(intent);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmation(etudiant);
            }
        });

        return convertView;
    }

    private void showDeleteConfirmation(final Etudiant etudiant) {
        new AlertDialog.Builder(getContext())
                .setTitle("Confirmation")
                .setMessage("Êtes vous sûr de vouloir supprimer cette filière?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        etudiantList.remove(etudiant);
                        EtudiantAdapter.this.notifyDataSetChanged();
                        sendDeleteRequest(etudiant);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void sendDeleteRequest(Etudiant etudiant) {
        String deleteUrl = url + etudiant.getId();

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.DELETE, deleteUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context.getApplicationContext(), "Etudiant Deleted Successfully!", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error+"");
            }
        });

        requestQueue.add(request);
    }
}
