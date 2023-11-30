package com.example.psrikandi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class pengaturanakun extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturanakun);
        // Inisialisasi ImageView
        ImageView imageViewAddress = findViewById(R.id.imageViewAddress);
        ImageView imageViewProfile = findViewById(R.id.imageViewProfile);
        ImageView imageViewAccount = findViewById(R.id.imageViewAccount);

        // Menambahkan onClickListener untuk setiap ImageView
        imageViewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddressMenu();
            }
        });

        imageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToProfileMenu();
            }
        });

        imageViewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAccountMenu();
            }
        });
    }

    // Metode untuk pindah ke menu alamat
    public void goToAddressMenu() {
        Intent intent = new Intent(this, EditAllamat.class);
        startActivity(intent);
    }

    // Metode untuk pindah ke menu profil
    public void goToProfileMenu() {
        Intent intent = new Intent(this, EditProfil.class);
        startActivity(intent);
    }

    // Metode untuk pindah ke menu akun
    public void goToAccountMenu() {
        Intent intent = new Intent(this, Account.class);
        startActivity(intent);
    }
}