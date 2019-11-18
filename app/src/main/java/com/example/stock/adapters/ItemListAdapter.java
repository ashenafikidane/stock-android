package com.example.stock.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stock.R;
import com.example.stock.models.Items;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

    private List<Items> items;
    private Context mContext;

    public ItemListAdapter(List<Items> items, Context context) {
        this.items = items;
        this.mContext = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_list_row, parent, false);

        return new ViewHolder(itemView);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView itemName;
        public TextView itemCount;
        public TextView itemInput;

        public ViewHolder(@NonNull View view) {
            super(view);
            itemName = view.findViewById(R.id.account_name);
            itemCount = view.findViewById(R.id.item_count);
            itemInput = view.findViewById(R.id.item_input);

        }

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        final Items item = this.items.get(position);
        holder.itemName.setText(item.getItemName());
        holder.itemCount.setText( mContext.getString(R.string.stock_total_item) +" "+ item.getItemCount());


    }

    @Override
    public int getItemCount() {
        return this.items != null ? this.items.size() : 0;
    }

}
