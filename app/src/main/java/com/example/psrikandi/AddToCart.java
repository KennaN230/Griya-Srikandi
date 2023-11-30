package com.example.psrikandi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.psrikandi.Cart.CartItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AddToCart extends AppCompatActivity {
    private static final String PREF_NAME = "cart_prefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        Intent intent = getIntent();
        if (intent != null) {
            String namaProduk = intent.getStringExtra("nama_produk");
            //double hargaProduk = intent.getDoubleExtra("harga_produk", 0.0);
            double hargaProduk = Double.parseDouble(intent.getStringExtra("harga_produk"));

            // Gunakan data yang diterima
            TextView namaProdukTextView = findViewById(R.id.productNameTextView);
            TextView hargaProdukTextView = findViewById(R.id.productPriceTextView);

            namaProdukTextView.setText("Nama Produk: " + namaProduk);
            hargaProdukTextView.setText("Harga Produk: " + hargaProduk);

            int quantity = intent.getIntExtra("quantity", 1); // Assuming default quantity is 1

            double totalHarga = hargaProduk * quantity;

            // Use this totalHarga as needed, e.g., display it in a TextView
            TextView totalHargaTextView = findViewById(R.id.totalTextView);
            totalHargaTextView.setText("Total Harga: " + totalHarga);

            Button saveButton = findViewById(R.id.addToCartButton);
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    EditText quantityEditText = findViewById(R.id.quantityEditText);
                    int userInputQuantity = Integer.parseInt(quantityEditText.getText().toString());

                    saveDataToSharedPreferences(namaProduk, hargaProduk, quantity, totalHarga);
                }
            });
        }
    }

    private void saveDataToSharedPreferences(String namaProduk, double hargaProduk, int userInputQuantity, double totalHarga) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        // Ambil data keranjang belanja dari SharedPreferences
        String cartJson = sharedPreferences.getString("cart", "");
        Type type = new TypeToken<List<CartItem>>() {}.getType();
        List<CartItem> cartItems = gson.fromJson(cartJson, type);

        // Jika cartItems null, inisialisasi dengan ArrayList baru
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        // Tambahkan produk baru ke dalam keranjang belanja
        cartItems.add(new CartItem(namaProduk, Double.valueOf(hargaProduk), userInputQuantity, totalHarga));

        // Simpan kembali data keranjang belanja ke SharedPreferences
        String newCartJson = gson.toJson(cartItems);
        editor.putString("cart", newCartJson);
        editor.apply();
    }
}