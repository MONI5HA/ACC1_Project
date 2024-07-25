package com.example.androidapp_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PricesAdapter extends RecyclerView.Adapter<PricesAdapter.PriceViewHolder> {

    private List<String> prices;

    public PricesAdapter(List<String> prices) {
        this.prices = prices;
    }

    @NonNull
    @Override
    public PriceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.price_item, parent, false);
        return new PriceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PriceViewHolder holder, int position) {
        holder.priceTextView.setText(prices.get(position));
    }

    @Override
    public int getItemCount() {
        return prices.size();
    }

    public void updatePrices(List<String> newPrices) {
        prices.clear();
        prices.addAll(newPrices);
        notifyDataSetChanged();
    }

    public static class PriceViewHolder extends RecyclerView.ViewHolder {
        TextView priceTextView;

        public PriceViewHolder(@NonNull View itemView) {
            super(itemView);
            priceTextView = itemView.findViewById(R.id.price_text_view);
        }
    }
}
