package com.example.android.shoppinglist.service;

import android.content.Context;

import com.example.android.shoppinglist.dao.AppDatabase;
import com.example.android.shoppinglist.model.SLItem;

import java.util.List;

import androidx.room.Room;

public class SLItemOfflineService implements ISLItemService {

    private final AppDatabase db;

    public SLItemOfflineService(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "shopping-list-db")
                .allowMainThreadQueries()   //For Test, TODO: remove it and introduce asynch queries / LiveData
                .fallbackToDestructiveMigration()       //For Test. Can it remain anyway?
                .build();
    }

    @Override
    public List<SLItem> getAll() {
        return db.slItemDAO().getAll();
    }

    @Override
    public SLItem findById(long id) {
        return db.slItemDAO().findById(id);
    }

    @Override
    public void insert(SLItem slItem) {
        db.slItemDAO().insert(slItem);
    }

    @Override
    public void update(SLItem slItem) {
        db.slItemDAO().update(slItem);
    }

    @Override
    public void remove(SLItem slItem) {

    }
}
