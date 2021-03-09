package com.example.list.viewModel;

import android.app.Application;
import android.content.ClipData;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.list.db.AppDatabase;
import com.example.list.db.Items;

import java.util.List;

public class FragmentViewModel extends AndroidViewModel {
    private MutableLiveData<List<Items>> ListOfItems;
    private AppDatabase appDatabase;


    public FragmentViewModel(Application application){
        super(application);
        ListOfItems = new MutableLiveData<>();
        appDatabase = AppDatabase.getDBInstance(getApplication().getApplicationContext());
    }

    public MutableLiveData<List<Items>> getItemListObserver(){
        return ListOfItems;
    }

    public void getAllItemList(int category){
        List<Items> categoryList =  appDatabase.shoppingListDao().getAllItemsList(category);
        if(categoryList.size() >0){
            ListOfItems.postValue(categoryList);
        }else{
            ListOfItems.postValue(null);
        }
    }
    public int getItemCount(int category){
        return appDatabase.shoppingListDao().getItemCount(category);
    }
    public void InsertItem(int catId,String itemName,int amount,String imgUrl){
        Items item = new Items();
        item.categoryId = catId;
        item.ItemName = itemName;
        item.Amount = amount;
        item.ImgUrl = imgUrl;
        appDatabase.shoppingListDao().insertItems(item);
    }
    public void deleteItem(Items item){
        appDatabase.shoppingListDao().deleteItems(item);
        getAllItemList(3);
    }

}
