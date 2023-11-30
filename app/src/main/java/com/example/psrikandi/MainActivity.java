package com.example.psrikandi;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.psrikandi.R;
import com.example.psrikandi.Register;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        requestQueue = Volley.newRequestQueue(this);

        String belumPunyaAkunText = "Belum mempunyai akun? ";
        String registerSekarangText = "Register sekarang!";

        SpannableString spannableString = new SpannableString(belumPunyaAkunText + registerSekarangText);

        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, belumPunyaAkunText.length(), 0);

        TextView forgotPasswordTextView = findViewById(R.id.forgotPasswordTextView);
        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aksi yang dilakukan saat teks "Lupa Password?" diklik
                // TODO: Tambahkan logika untuk pengaturan lupa password
                Intent intent = new Intent(MainActivity.this, LupaPassword.class);
                startActivity(intent);
            }
        });

        // Menambahkan aksi untuk teks "Belum punya akun? Segera daftar disini."
        TextView registerTextView = findViewById(R.id.registerTextView);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aksi yang dilakukan saat teks "Belum punya akun? Segera daftar disini." diklik
                // TODO: Tambahkan logika untuk navigasi ke halaman pendaftaran
                // Contoh sederhana: Navigasi ke halaman pendaftaran (RegisterActivity)
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (!username.isEmpty() && !password.isEmpty()) {
                    String apiUrl = "http://192.168.211.105//Griya/login.php"; // Ganti dengan URL login API Anda

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, apiUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonResponse = new JSONObject(response);
                                        boolean success = jsonResponse.getBoolean("success");
                                        String message = jsonResponse.getString("message");

                                        if (success) {
                                            Intent intent = new Intent(MainActivity.this, FragControl.class);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(MainActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                    Toast.makeText(MainActivity.this, "Terjadi kesalahan2", Toast.LENGTH_SHORT).show();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("NKonsumen", username);
                            params.put("Password", password);
                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);
                } else {
                    Toast.makeText(MainActivity.this, "Username dan password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
