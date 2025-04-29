package com.example.scholarshelf;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TextbookAdapter extends RecyclerView.Adapter<TextbookAdapter.TextbookViewHolder> {

    private final List<Textbook> textbookList;
    private List<Textbook> fullList;

    public TextbookAdapter(List<Textbook> textbookList) {
        this.textbookList = textbookList;
        this.fullList = new ArrayList<>(textbookList);
    }

    public void updateFullList(List<Textbook> updatedList) {
        this.fullList = updatedList;
    }

    public void filter(String text) {
        List<Textbook> filteredList = new ArrayList<>();
        if (text.isEmpty()) {
            filteredList.addAll(fullList);
        } else {
            text = text.toLowerCase();
            for (Textbook b : fullList) {
                if (b.getTitle().toLowerCase().contains(text) ||
                        b.getSellerName().toLowerCase().contains(text)) {
                    filteredList.add(b);
                }
            }
        }
        textbookList.clear();
        textbookList.addAll(filteredList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TextbookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.textbook_item, parent, false);
        return new TextbookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TextbookViewHolder holder, int position) {
        Textbook textbook = textbookList.get(position);
        holder.title.setText(holder.itemView.getContext().getString(R.string.title_label, textbook.getTitle()));
        holder.seller.setText(holder.itemView.getContext().getString(R.string.seller_label, textbook.getSellerName()));
        holder.price.setText(holder.itemView.getContext().getString(R.string.price_label, textbook.getPrice()));
    }

    @Override
    public int getItemCount() {
        return textbookList.size();
    }

    public static class TextbookViewHolder extends RecyclerView.ViewHolder {
        TextView title, seller, price;

        public TextbookViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleView);
            seller = itemView.findViewById(R.id.sellerView);
            price = itemView.findViewById(R.id.priceView);
        }
    }
}
