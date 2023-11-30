package com.example.psrikandi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.psrikandi.Api.ApiServiceFlash;
import com.example.psrikandi.flashAdapter.FlashSaleAdapter;
import com.example.psrikandi.flashAdapter.FlashSaleItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlashSale extends Fragment {
    private RecyclerView recyclerView;
    private FlashSaleAdapter adapter;
    private List<FlashSaleItem> flashSaleItems;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_flash_sale, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        flashSaleItems = new ArrayList<>();
        adapter = new FlashSaleAdapter(getContext(), flashSaleItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        loadData(); // Panggil fungsi untuk mengambil data flash sale

        return view;
    }
    private void loadData() {
        ApiServiceFlash apiService = RetrofitClient.getClient().create(ApiServiceFlash.class);

        Call<List<FlashSaleItem>> call = apiService.getFlashSale();
        call.enqueue(new Callback<List<FlashSaleItem>>() {
            @Override
            public void onResponse(Call<List<FlashSaleItem>> call, Response<List<FlashSaleItem>> response) {
                if (response.isSuccessful()) {
                    flashSaleItems.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    // Handle response error
                    Toast.makeText(getContext(), "Failed to get flash sale data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FlashSaleItem>> call, Throwable t) {
                // Handle failure
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}