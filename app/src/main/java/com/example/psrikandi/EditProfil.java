package com.example.psrikandi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class EditProfil extends AppCompatActivity {
    private EditText editTextRecipientName, editTextRecipientPhone, editTextFullAddress;
    private Button buttonSaveAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        editTextRecipientName = findViewById(R.id.editTextUsername);
        editTextRecipientPhone = findViewById(R.id.editTextPhoneNumber);
        editTextFullAddress = findViewById(R.id.editTextEmail);
        buttonSaveAddress = findViewById(R.id.buttonSaveProfile);

        buttonSaveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAddress();
            }
        });
    }

    private void saveAddress() {
        String url = "http://your-api-url.com/edit_alamat.php";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("recipient_name", editTextRecipientName.getText().toString());
            jsonObject.put("recipient_phone", editTextRecipientPhone.getText().toString());
            jsonObject.put("full_address", editTextFullAddress.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean success = response.getBoolean("success");
                            if (success) {
                                Toast.makeText(EditProfil.this, "Alamat berhasil disimpan", Toast.LENGTH_SHORT).show();
                                // Tambahkan logika lain jika diperlukan
                            } else {
                                Toast.makeText(EditProfil.this, "Gagal menyimpan alamat", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditProfil.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
}