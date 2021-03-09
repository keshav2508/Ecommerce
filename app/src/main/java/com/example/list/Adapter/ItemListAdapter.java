package com.example.list.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.list.R;
import com.example.list.db.Items;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.MyViewHolder> {

    private Context context;
    private List<Items> itemsList;
    HandleCategoryClick clickListener;
    public ItemListAdapter(Context context, HandleCategoryClick clickListener){
        this.context=context;
        this.clickListener=clickListener;
    }

    public void setItemList(List<Items> itemList){
        this.itemsList=itemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListAdapter.MyViewHolder holder, int position) {

        holder.itemName.setText(this.itemsList.get(position).ItemName);
        String x="₹" +String.valueOf(this.itemsList.get(position).Amount);
        holder.amount.setText(x);
        /*Glide.with(context)
                .load(this.itemsList.get(position).ImgUrl)
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imageView);*/
        holder.AddToCart.setOnClickListener(v -> {
            clickListener.itemClick(itemsList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        if(itemsList==null || itemsList.size()==0) return 0;
        else return itemsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemName;
        TextView amount;
        ImageView imageView;
        Button AddToCart;
        public MyViewHolder(View view){
            super(view);
            itemName = view.findViewById(R.id.itemName);
            amount = view.findViewById(R.id.Amount);
            imageView = view.findViewById(R.id.image);
            AddToCart = view.findViewById(R.id.AddToCart);
        }
    }
    public interface HandleCategoryClick{
        void itemClick(Items item);
    }

}
