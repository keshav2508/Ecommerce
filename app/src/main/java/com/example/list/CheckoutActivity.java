package com.example.list;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Dao;

import android.content.DialogInterface;
import android.media.ImageReader;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.list.Adapter.ItemListAdapter;
import com.example.list.Adapter.checkoutListAdapter;
import com.example.list.db.Items;
import com.example.list.viewModel.FragmentViewModel;

public class CheckoutActivity extends AppCompatActivity implements checkoutListAdapter.HandleCategoryClick{
    private FragmentViewModel viewModel;
    private RecyclerView recyclerView;
    private checkoutListAdapter checkoutListAdapter;
    LinearLayout Done;
    TextView ItemInCartCheckout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        ItemInCartCheckout = findViewById(R.id.itemInCartCheckout);


        recyclerView = findViewById(R.id.recyclerViewCheckout);
        Done = findViewById(R.id.done);
        initViewModel();
        initRecyclerView();

        int itemsCount = viewModel.getItemCount(3);
        ItemInCartCheckout.setText(String.valueOf(itemsCount));

        viewModel.getAllItemList(3);
        Done.setOnClickListener(v -> {
            showDialog();
        });

    }
    public void showDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(
                CheckoutActivity.this);
        builder.setTitle("Successful");
        builder.setMessage("Your Order has been Placed Successfully");
        builder.setPositiveButton("OK",
                (dialog, which) -> Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show());
        builder.show();
    }
    public void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        checkoutListAdapter= new checkoutListAdapter(this,this);
        recyclerView.setAdapter(checkoutListAdapter);
    }
    public void initViewModel(){
        viewModel = new ViewModelProvider(this).get(FragmentViewModel.class);
        viewModel.getItemListObserver().observe(this, items -> {
            if(items==null){
                recyclerView.setVisibility(View.GONE);
            }else{
                recyclerView.setVisibility(View.VISIBLE);
                checkoutListAdapter.setItemList(items);
            }
        });
    }

    @Override
    public void deleteClick(Items item) {
        viewModel.deleteItem(item);
        String itemsincart = ItemInCartCheckout.getText().toString();
        int i = Integer.parseInt(itemsincart);
        i--;
        String a = Integer.valueOf(i).toString();
        ItemInCartCheckout.setText(a);
    }
}