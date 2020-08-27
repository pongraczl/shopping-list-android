package com.example.android.shoppinglist.service;

import com.example.android.shoppinglist.model.SLItem;

import java.util.List;

//TODO: later (REST API + synch with SQLite/Room)
public class SLItemOnlineService implements ISLItemService {
    @Override
    public List<SLItem> getAll() {
        return null;
    }

    @Override
    public SLItem findById(long id) {
        return null;
    }

    @Override
    public void insert(SLItem slItem) {

    }

    @Override
    public void update(SLItem slItem) {

    }

    @Override
    public void remove(SLItem slItem) {

    }
}
