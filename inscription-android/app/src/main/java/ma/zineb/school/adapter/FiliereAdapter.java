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

import ma.zineb.school.AddFiliere;
import ma.zineb.school.EditFiliere;
import ma.zineb.school.ListFilieres;
import ma.zineb.school.R;
import ma.zineb.school.beans.Filiere;

public class FiliereAdapter extends ArrayAdapter<Filiere> {
    private List<Filiere> filiereList;
    private Context context;

    private String url = "http://192.168.1.3:8082/api/filieres/";

    public FiliereAdapter(Context context, List<Filiere> filiereList) {
        super(context, 0, filiereList);
        this.filiereList = filiereList;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Filiere filiere = filiereList.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_filiere_item_layout, parent, false);
        }

        TextView id = convertView.findViewById(R.id.filiereID);
        TextView name = convertView.findViewById(R.id.filiereName);
        TextView code = convertView.findViewById(R.id.filiereCode);

        id.setText(filiere.getId()+"");
        name.setText(filiere.getName());
        code.setText(filiere.getCode());

        ImageButton buttonEdit = convertView.findViewById(R.id.buttonEdit);
        ImageButton buttonDelete = convertView.findViewById(R.id.buttonDelete);


        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditFiliere.class);
                intent.putExtra("id", filiere.getId());
                intent.putExtra("code", filiere.getCode());
                intent.putExtra("name", filiere.getName());
                context.startActivity(intent);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmation(filiere);
            }
        });

        return convertView;
    }

    private void showDeleteConfirmation(final Filiere filiere) {
        new AlertDialog.Builder(getContext())
                .setTitle("Confirmation")
                .setMessage("Êtes vous sûr de vouloir supprimer cette filière?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        filiereList.remove(filiere);
                        FiliereAdapter.this.notifyDataSetChanged();
                        sendDeleteRequest(filiere);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void sendDeleteRequest(Filiere filiere) {
        String deleteUrl = url + filiere.getId();

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.DELETE, deleteUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context.getApplicationContext(), "Filiere Deleted Successfully!", Toast.LENGTH_SHORT).show();
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
