package com.example.psrikandi.Cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psrikandi.R;

import java.util.List;

public class CartAdapter extends BaseAdapter {
    private List<CartItem> cartItemList;
    private LayoutInflater inflater;

    public CartAdapter(Context context, List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cartItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.cart_item, parent, false);
            holder = new ViewHolder();
            holder.productNameTextView = convertView.findViewById(R.id.productNameTextView1);
            holder.quantityTextView = convertView.findViewById(R.id.quantityTextView1);
            holder.priceTextView = convertView.findViewById(R.id.priceTextView1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CartItem cartItem = cartItemList.get(position);
        holder.productNameTextView.setText(cartItem.getProductName());
        holder.quantityTextView.setText(String.valueOf(cartItem.getQuantity()));
        holder.priceTextView.setText("$" + cartItem.getPrice());

        return convertView;
    }

    static class ViewHolder {
        TextView productNameTextView;
        TextView quantityTextView;
        TextView priceTextView;
    }
}
