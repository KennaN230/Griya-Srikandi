package com.example.psrikandi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Metode extends AppCompatActivity {

    private TextView paymentTitle, paymentDescription;
    private EditText paymentAmount;
    private Button payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metode);

        paymentTitle = findViewById(R.id.paymentTitle);
        paymentDescription = findViewById(R.id.paymentDescription);
        paymentAmount = findViewById(R.id.paymentAmount);
        payButton = findViewById(R.id.payButton);

        // Atur listener untuk tombol bayar
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Panggil metode untuk melakukan pembayaran
                processPayment();
            }
        });
    }

    private void processPayment() {
        // Ambil nilai dari EditText jumlah pembayaran
        String amountString = paymentAmount.getText().toString();

        // Cek apakah jumlah pembayaran tidak kosong
        if (!amountString.isEmpty()) {
            // Konversi nilai jumlah pembayaran ke tipe data yang sesuai (misalnya, double)
            double amount = Double.parseDouble(amountString);

            // Lakukan logika pembayaran sesuai kebutuhan aplikasi Anda
            // Di sini, kita hanya menampilkan pesan Toast sebagai contoh
            String paymentMessage = "Pembayaran sebesar " + amount + " berhasil!";
            Toast.makeText(this, paymentMessage, Toast.LENGTH_SHORT).show();
        } else {
            // Tampilkan pesan bahwa jumlah pembayaran tidak boleh kosong
            Toast.makeText(this, "Masukkan jumlah pembayaran", Toast.LENGTH_SHORT).show();
        }
    }
}