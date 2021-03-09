package com.example.list.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.list.R;
import com.example.list.db.Items;

import java.util.List;

public class checkoutListAdapter extends RecyclerView.Adapter<checkoutListAdapter.MyViewHolder> {
    private Context context;
    private List<Items> itemsList;
    checkoutListAdapter.HandleCategoryClick clickListener;
    public checkoutListAdapter(Context context, checkoutListAdapter.HandleCategoryClick clickListener){
        this.context=context;
        this.clickListener=clickListener;
    }

    public void setItemList(List<Items> itemList){
        this.itemsList=itemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public checkoutListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.checkout_list_row,parent,false);
        return new checkoutListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull checkoutListAdapter.MyViewHolder holder, int position) {

        holder.itemName.setText(this.itemsList.get(position).ItemName);
        holder.Amount.setText(String.valueOf(this.itemsList.get(position).Amount));
        /*Glide.with(context)
                .load(this.itemsList.get(position).ImgUrl)
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imageView);*/
        holder.delete.setOnClickListener(v -> {
            clickListener.deleteClick(itemsList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        if(itemsList==null || itemsList.size()==0) return 0;
        else return itemsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemName;
        TextView Amount;
        ImageView imageView;
        ImageView delete;
        public MyViewHolder(View view){
            super(view);
            itemName = view.findViewById(R.id.itemNameCheckout);
            Amount = view.findViewById(R.id.AmountCheckout);
            imageView = view.findViewById(R.id.imageCheckout);
            delete = view.findViewById(R.id.deleteItemCheckout);
        }
    }
    public interface HandleCategoryClick{
        void deleteClick(Items itme);
    }

}
