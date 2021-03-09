package com.example.list.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Items {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "categoryId")
    public int categoryId;

    @ColumnInfo(name = "ItemName")
    public String ItemName;

    @ColumnInfo(name = "ImgUrl")
    public String ImgUrl;

    @ColumnInfo(name = "Amount")
    public int Amount;
}
