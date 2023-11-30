package com.example.psrikandi.produkAdapter;

public class ProductModel {
    private String KProduk;
    private String NProduk;
    private String HJual;
    private String imageUrl; // Sesuaikan dengan nama variabel

    public ProductModel(String KProduk, String NProduk, String HJual, String ImageBase64) {
        this.KProduk = KProduk;
        this.NProduk = NProduk;
        this.HJual = HJual;
        this.imageUrl = ImageBase64;
    }

    public String getKProduk() {
        return KProduk;
    }

    public String getNProduk() {
        return NProduk;
    }

    public String getHJual() {
        return HJual;
    }

    public String getImageBase64() {
        return imageUrl;
    }
}
