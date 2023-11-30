package com.example.psrikandi.flashAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.psrikandi.R;

import java.util.List;

public class FlashSaleAdapter extends RecyclerView.Adapter<FlashSaleAdapter.ViewHolder> {
    private List<FlashSaleItem> flashSaleItems;
    private Context context;

    public FlashSaleAdapter(Context context, List<FlashSaleItem> flashSaleItems) {
        this.context = context;
        this.flashSaleItems = flashSaleItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.flash_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FlashSaleItem flashSaleItem = flashSaleItems.get(position);

        holder.productNameTextView.setText(flashSaleItem.getProductName());
        holder.originalPriceTextView.setText("Original Price: $" + flashSaleItem.getOriginalPrice());
        holder.discountTextView.setText("Discount: " + flashSaleItem.getDiscountPercentage() + "%");
        holder.flashSalePriceTextView.setText("Flash Sale Price: $" + flashSaleItem.getFlashSalePrice());
        holder.endTimeTextView.setText("End Time: " + flashSaleItem.getEndTime());
    }

    @Override
    public int getItemCount() {
        return flashSaleItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView productNameTextView;
        TextView originalPriceTextView;
        TextView discountTextView;
        TextView flashSalePriceTextView;
        TextView endTimeTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            originalPriceTextView = itemView.findViewById(R.id.originalPriceTextView);
            discountTextView = itemView.findViewById(R.id.discountTextView);
            flashSalePriceTextView = itemView.findViewById(R.id.flashSalePriceTextView);
            endTimeTextView = itemView.findViewById(R.id.endTimeTextView);
        }
    }
}
