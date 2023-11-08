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

import ma.zineb.school.AddRole;
import ma.zineb.school.EditRole;
import ma.zineb.school.ListRoles;
import ma.zineb.school.R;
import ma.zineb.school.beans.Role;

public class RoleAdapter extends ArrayAdapter<Role> {
    private List<Role> roleList;
    private Context context;

    private String url = "http://192.168.43.184:8082/api/roles/";

    public RoleAdapter(Context context, List<Role> roleList) {
        super(context, 0, roleList);
        this.roleList = roleList;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Role role = roleList.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_role_item_layout, parent, false);
        }

        TextView name = convertView.findViewById(R.id.roleName);
        TextView id = convertView.findViewById(R.id.roleID);

        name.setText(role.getName());
        id.setText(role.getId()+"");

        ImageButton buttonEdit = convertView.findViewById(R.id.buttonEdit);
        ImageButton buttonDelete = convertView.findViewById(R.id.buttonDelete);


        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditRole.class);
                intent.putExtra("id", role.getId());
                intent.putExtra("name", role.getName());
                context.startActivity(intent);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmation(role);
            }
        });

        return convertView;
    }

    private void showDeleteConfirmation(final Role role) {
        new AlertDialog.Builder(getContext())
                .setTitle("Confirmation")
                .setMessage("Êtes vous sûr de vouloir supprimer ce rôle?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        roleList.remove(role);
                        RoleAdapter.this.notifyDataSetChanged();
                        sendDeleteRequest(role);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void sendDeleteRequest(Role role) {
        String deleteUrl = url + role.getId();

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.DELETE, deleteUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context.getApplicationContext(), "Rôle Deleted Successfully!", Toast.LENGTH_SHORT).show();
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
