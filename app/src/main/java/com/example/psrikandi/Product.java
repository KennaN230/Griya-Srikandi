// Product.java
package com.example.psrikandi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.psrikandi.Api.ApiService;
import com.example.psrikandi.produkAdapter.ProductAdapter;
import com.example.psrikandi.produkAdapter.ProductModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Product extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        // Set layout manager for RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Panggil method untuk memuat data
        loadData();
        return view;
    }

    private void loadData() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<List<ProductModel>> call = apiService.getProducts();

        call.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<ProductModel>> call, @NonNull Response<List<ProductModel>> response) {
                if (response.isSuccessful()) {
                    List<ProductModel> productList = response.body();
                    showData(productList);
                } else {
                    // Handle failure
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ProductModel>> call, @NonNull Throwable t) {
                // Handle failure
            }
        });
    }

    private void showData(List<ProductModel> productList) {
        // Tampilkan data dalam RecyclerView
        productAdapter = new ProductAdapter(getContext(), productList);
        recyclerView.setAdapter(productAdapter);

        productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ProductModel product) {
                // Panggil metode untuk berpindah ke aktivitas lain
                goToAnotherClass(product);
            }
        });
    }

    private void goToAnotherClass(ProductModel product) {
        // Menyiapkan Intent untuk pindah ke halaman baru (gantilah dengan kelas aktivitas yang sesuai)
        Intent intent = new Intent(getActivity(), AddToCart.class);

        // Menyisipkan data produk ke Intent
        intent.putExtra("nama_produk", product.getNProduk());

        String hargaString = String.valueOf(product.getHJual());
        intent.putExtra("harga_produk", hargaString);

        // Menjalankan aktivitas baru
        startActivity(intent);
    }
}
