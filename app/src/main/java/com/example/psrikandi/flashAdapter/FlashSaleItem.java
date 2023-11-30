package com.example.psrikandi.flashAdapter;

public class FlashSaleItem {
    private String productName;
    private double originalPrice;
    private int discountPercentage;
    private double flashSalePrice;
    private String endTime;

    public FlashSaleItem(String productName, double originalPrice, int discountPercentage, double flashSalePrice, String endTime) {
        this.productName = productName;
        this.originalPrice = originalPrice;
        this.discountPercentage = discountPercentage;
        this.flashSalePrice = flashSalePrice;
        this.endTime = endTime;
    }

    public String getProductName() {
        return productName;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public double getFlashSalePrice() {
        return flashSalePrice;
    }

    public String getEndTime() {
        return endTime;
    }
}
