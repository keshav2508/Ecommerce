package com.example.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.list.Adapter.ItemListAdapter;
import com.example.list.Adapter.ViewPagerAdapter;
import com.example.list.db.Items;
import com.example.list.viewModel.FragmentViewModel;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout checkout;
    TextView ItemInCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkout = findViewById(R.id.checkout);
        checkout.setOnClickListener(v -> {
            Intent i = new Intent(this, CheckoutActivity.class);
            startActivity(i);
        });

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(viewPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        ItemInCart = findViewById(R.id.itemInCart);


    }
    void showCartItem(String num){
        ItemInCart.setText(num);
    }

    void showIncrement(){
        String itemsincart = ItemInCart.getText().toString();
        int i = Integer.parseInt(itemsincart);
        i++;
        String a = Integer.valueOf(i).toString();
        ItemInCart.setText(a);
    }

}