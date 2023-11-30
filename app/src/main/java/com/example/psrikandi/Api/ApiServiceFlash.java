package com.example.psrikandi.Api;

import com.example.psrikandi.flashAdapter.FlashSaleItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceFlash {
    @GET("tampil.php")
    Call<List<FlashSaleItem>> getFlashSale();
}
