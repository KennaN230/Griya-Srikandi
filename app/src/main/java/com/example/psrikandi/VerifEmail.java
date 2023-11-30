package com.example.psrikandi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
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

public class VerifEmail extends AppCompatActivity {
    private EditText emailEditText;
    private Button requestOTPButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verif_email);
        emailEditText = findViewById(R.id.passwordEditText);
        requestOTPButton = findViewById(R.id.loginButton);

        requestOTPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mengambil email dari pengguna
                String email = emailEditText.getText().toString().trim();

                // Memastikan email tidak kosong
                if (!email.isEmpty()) {
                    // Memulai AsyncTask untuk melakukan permintaan OTP ke server
                    new RequestOTPAsyncTask().execute(email);
                } else {
                    Toast.makeText(VerifEmail.this, "Masukkan alamat email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class RequestOTPAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String email = params[0];
            String serverUrl = "http://192.168.211.105//Griya/verifemail.php"; // Ganti dengan URL permintaan OTP di server Anda

            try {
                // Membuat objek URL
                URL url = new URL(serverUrl);

                // Membuka koneksi HTTP
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setDoOutput(true);

                // Membuat data yang akan dikirim ke server
                String postData = "Email=" + email;
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(postData.getBytes());
                outputStream.flush();
                outputStream.close();

                // Membaca respons dari server
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Menutup koneksi
                connection.disconnect();

                return response.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                // Mengurai JSON respons dari server
                JSONObject jsonObject = new JSONObject(result);
                String status = jsonObject.getString("status");
                String message = jsonObject.getString("message");

                // Menanggapi hasil respons
                if ("success".equals(status)) {
                    Toast.makeText(VerifEmail.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(VerifEmail.this, "Gagal: " + message, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}