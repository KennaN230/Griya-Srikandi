package com.example.psrikandi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LupaPassword extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;
    private Button updatePasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        updatePasswordButton = findViewById(R.id.updatePasswordButton);

        updatePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePassword();
            }
        });
    }

    private void updatePassword() {
        String username = usernameEditText.getText().toString();
        String newPassword = passwordEditText.getText().toString();

        if (username.isEmpty() || newPassword.isEmpty()) {
            Toast.makeText(this, "Username and newPassword are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kirim permintaan ke server
        new UpdatePasswordTask().execute(username, newPassword);
    }

    public class UpdatePasswordTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String username = params[0];
            String newPassword = params[1];

            try {
                URL url = new URL("http://192.168.115.105//Griya/lupapassword.php"); // Ganti dengan URL yang sesuai
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);

                // Kumpulkan data untuk dikirim
                String data = "NKonsumen=" + username + "&Password=" + newPassword;
                OutputStream os = connection.getOutputStream();
                os.write(data.getBytes());
                os.flush();
                os.close();

                // Baca respons dari server
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Log respons
                Log.d("ServerResponse", response.toString());

                // Tutup koneksi
                connection.disconnect();

                return response.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                try {
                    // Parsing respons JSON
                    JSONObject jsonResponse = new JSONObject(result);
                    String status = jsonResponse.getString("status");
                    String message = jsonResponse.getString("message");

                    // Tampilkan pesan ke pengguna
                    Toast.makeText(LupaPassword.this, message, Toast.LENGTH_SHORT).show();

                    // Handle lebih lanjut sesuai kebutuhan, misalnya, pergi ke halaman beranda jika berhasil
                    if ("success".equals(status)) {
                        // Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        // startActivity(intent);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(LupaPassword.this, "Failed to connect to server", Toast.LENGTH_SHORT).show();
            }
        }
    }
}