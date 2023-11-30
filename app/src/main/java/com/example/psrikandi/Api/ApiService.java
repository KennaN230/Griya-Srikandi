package com.example.psrikandi.Api;

import com.example.psrikandi.produkAdapter.ProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("tampil.php")
    Call<List<ProductModel>> getProducts();
}
