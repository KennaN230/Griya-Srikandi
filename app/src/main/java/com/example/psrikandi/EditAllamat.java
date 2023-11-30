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

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class EditAllamat extends AppCompatActivity {
    private EditText editTextRecipientName, editTextRecipientPhone, editTextFullAddress, editTextCity,
            editTextPostalCode, editTextProvince, editTextCountry;
    private Button buttonSaveAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_allamat);

        editTextRecipientName = findViewById(R.id.editTextRecipientName);
        editTextRecipientPhone = findViewById(R.id.editTextRecipientPhone);
        editTextFullAddress = findViewById(R.id.editTextFullAddress);
        buttonSaveAddress = findViewById(R.id.buttonSaveAddress);

        buttonSaveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAddress();
            }
        });
    }

    private void saveAddress() {
        String recipientName = editTextRecipientName.getText().toString().trim();
        String recipientPhone = editTextRecipientPhone.getText().toString().trim();
        String fullAddress = editTextFullAddress.getText().toString().trim();

        if (!recipientName.isEmpty() && !recipientPhone.isEmpty() && !fullAddress.isEmpty()) {
            // Menggunakan AsyncTask untuk memastikan koneksi jaringan berjalan di background
            SaveAddressTask saveAddressTask = new SaveAddressTask();
            saveAddressTask.execute(recipientName, recipientPhone, fullAddress);
        } else {
            Toast.makeText(this, "Mohon lengkapi semua field", Toast.LENGTH_SHORT).show();
        }
    }

    private class SaveAddressTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            String recipientName = params[0];
            String recipientPhone = params[1];
            String fullAddress = params[2];

            // Ganti dengan URL API Anda
            String apiUrl = "http://192.168.211.105//Griya/updatealamat.php";

            try {
                URL url = new URL(apiUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");

                // Menyiapkan data yang akan dikirim ke server
                String postData = "recipient_name=" + recipientName +
                        "&recipient_phone=" + recipientPhone +
                        "&full_address=" + fullAddress;

                // Mengirim data ke server
                urlConnection.setDoOutput(true);
                OutputStream out = urlConnection.getOutputStream();
                out.write(postData.getBytes());
                out.flush();
                out.close();

                // Menerima respons dari server
                Scanner scanner = new Scanner(urlConnection.getInputStream());
                StringBuilder response = new StringBuilder();
                while (scanner.hasNext()) {
                    response.append(scanner.next());
                }

                // Parsing respons JSON
                JSONObject jsonResponse = new JSONObject(response.toString());
                return jsonResponse.getBoolean("success");

            } catch (MalformedURLException | JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                Toast.makeText(EditAllamat.this, "Alamat berhasil disimpan", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(EditAllamat.this, "Gagal menyimpan alamat", Toast.LENGTH_SHORT).show();
            }
        }
    }
}