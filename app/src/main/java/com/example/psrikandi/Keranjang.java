package com.example.psrikandi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.psrikandi.Cart.Cart;
import com.example.psrikandi.Cart.CartAdapter;
import com.example.psrikandi.Cart.CartItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class Keranjang extends AppCompatActivity {
    private Cart cart;
    private CartAdapter cartAdapter;
    private TextView totalTextView, cartDataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);

        // Inisialisasi tampilan
        totalTextView = findViewById(R.id.totalTextView);
        cartDataTextView = findViewById(R.id.cartDataTextView);

        // Inisialisasi objek cart
        cart = new Cart();

        // Mengambil data dari SharedPreferences
        loadDataFromSharedPreferences();

        updateTotal();

        Button clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showClearCartConfirmation();
                updateTotal();
                updateCartDataTextView();
            }
        });
    }

    private void loadDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String cartJson = sharedPreferences.getString("cart", "");

        if (!TextUtils.isEmpty(cartJson)) {
            Type type = new TypeToken<List<CartItem>>() {}.getType();
            List<CartItem> cartItems = gson.fromJson(cartJson, type);
            cart = new Cart(cartItems);
            updateCartDataTextView();
        } else {
            cart = new Cart();  // Gunakan konstruktor tanpa parameter
        }
    }

    private void showClearCartConfirmation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Konfirmasi");
        builder.setMessage("BCA 0241326755");
        builder.setPositiveButton("Lampirkan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearCartSharedPreferences();
                openWhatsApp();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Tidak melakukan apa-apa jika pengguna memilih "Tidak"
            }
        });
        builder.show();
    }

    private void updateCartDataTextView() {
        StringBuilder cartData = new StringBuilder("Data dari SharedPreferences:\n");

        for (CartItem item : cart.getCartItems()) {
            cartData.append("Nama Produk: ").append(item.getProductName()).append("\n")
                    .append("Harga Produk: ").append(item.getPrice()).append("\n")
                    .append("Quantity: ").append(item.getQuantity()).append("\n");
        }

        cartDataTextView.setText(cartData.toString());
    }

    private void updateTotal() {
        double total = cart.calculateTotal();
        totalTextView.setText("Total : Rp. " + total);
    }

    private void clearCartSharedPreferences() {
        // Hapus data keranjang dari SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("cart");
        editor.apply();

        // Set ulang tampilan dan objek cart
        cart.clearCart();
        updateTotal();
        updateCartDataTextView();
    }

    private void openWhatsApp() {
        try {
            // Format nomor telepon sesuai dengan format URL WhatsApp
            String phoneNumber = "6282230551298"; // Ganti dengan nomor telepon yang sesuai
            String message = "Halo, selamat siang. Saya ingin melampirkan bukti pembayaran";
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
