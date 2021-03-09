package com.example.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.list.Adapter.ItemListAdapter;
import com.example.list.db.Items;
import com.example.list.viewModel.FragmentViewModel;

public class SecondFragment extends Fragment implements ItemListAdapter.HandleCategoryClick{

    private FragmentViewModel fragmentViewModel;
    private RecyclerView recyclerView;
    private ItemListAdapter itemListAdapter;


    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void initRecyclerView(){

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        itemListAdapter= new ItemListAdapter(getContext(),this);
        recyclerView.setAdapter(itemListAdapter);
    }
    public void initViewModel(){
        fragmentViewModel = new ViewModelProvider(this).get(FragmentViewModel.class);
        fragmentViewModel.getItemListObserver().observe(getViewLifecycleOwner(), categories -> {
            if(categories==null){
                recyclerView.setVisibility(View.GONE);
            }else{
                recyclerView.setVisibility(View.VISIBLE);
                itemListAdapter.setItemList(categories);
            }
        });
    }
    public void creatingList(){
        String[] item2Name={"dell","hp","Lenovo","Apple","Vaio"};
        Integer[] item2Amount={80000,75000,55000,120000,64000};
        String[] img2Url={"https://avatars.githubusercontent.com/u/58911749?v=4"};
        for(int i=0;i<5;i++){
            fragmentViewModel.InsertItem(2,item2Name[i],item2Amount[i],img2Url[0]);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_first_fragment,container,false);
        recyclerView = view.findViewById(R.id.recyclerView);
        initViewModel();
        creatingList();
        initRecyclerView();
        int items=fragmentViewModel.getItemCount(3);
        ((MainActivity)getActivity()).showCartItem(String.valueOf(items));
        fragmentViewModel.getAllItemList(2);
        return view;
    }

    @Override
    public void itemClick(Items item) {
        ((MainActivity)getActivity()).showIncrement();
        fragmentViewModel.InsertItem(3,item.ItemName,item.Amount,item.ImgUrl);

    }
}