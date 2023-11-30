package com.example.psrikandi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class User extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        ImageView waImageView = view.findViewById(R.id.Wa);
        ImageView paImageView2 = view.findViewById(R.id.Pa);
        ImageView psImageView3 = view.findViewById(R.id.Ps);

        // Menambahkan OnClickListener ke ImageView
        waImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatsApp();
            }
        });
        paImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mendapatkan referensi ke aktivitas terkait dengan fragment
                if (getActivity() != null) {
                    // Menyiapkan Intent untuk pindah ke halaman baru (gantilah dengan kelas aktivitas yang sesuai)
                    Intent intent = new Intent(getActivity(), pengaturanakun.class);

                    // Menjalankan aktivitas baru
                    startActivity(intent);
                }
            }
        });
        psImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mendapatkan referensi ke aktivitas terkait dengan fragment
                if (getActivity() != null) {
                    // Menyiapkan Intent untuk pindah ke halaman baru (gantilah dengan kelas aktivitas yang sesuai)
                    Intent intent = new Intent(getActivity(), Keranjang.class);

                    // Menjalankan aktivitas baru
                    startActivity(intent);
                }
            }
        });
        return view;
    }

    private void openWhatsApp() {
        try {
            // Format nomor telepon sesuai dengan format URL WhatsApp
            String phoneNumber = "6282230551298"; // Ganti dengan nomor telepon yang sesuai
            String message = "Halo, selamat siang";
            String encodedMessage = Uri.encode(message);
            // Buat URI untuk membuka WhatsApp
            Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=" + phoneNumber + "&text=" + encodedMessage);

            // Buat intent untuk membuka WhatsApp
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            // Mulai aktivitas intent
            startActivity(intent);
        } catch (Exception e) {
            // Tangani kesalahan jika WhatsApp tidak terinstal
            e.printStackTrace();
        }
    }
}
