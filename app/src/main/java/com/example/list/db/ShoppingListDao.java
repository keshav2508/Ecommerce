package com.example.list.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ShoppingListDao {

    @Query("Select * from Items where categoryId =:catID")
    List<Items> getAllItemsList(int catID);

    @Insert
    void insertItems(Items items);


    @Delete
    void deleteItems(Items items);

    @Query("Select COUNT(uid) from Items where categoryId = :catID2")
    int getItemCount(int catID2);

}
