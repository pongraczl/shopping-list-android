package com.example.android.shoppinglist.dao;

import com.example.android.shoppinglist.model.SLItem;
import com.example.android.shoppinglist.service.ISLItemService;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {SLItem.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SLItemRoomDAO slItemDAO();
}
