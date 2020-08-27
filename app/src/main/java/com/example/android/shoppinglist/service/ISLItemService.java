package com.example.android.shoppinglist.service;

import com.example.android.shoppinglist.model.SLItem;

import java.util.List;

public interface ISLItemService {

    public List<SLItem> getAll();

    public SLItem findById(long id);

    public void insert(SLItem slItem);

    public void update(SLItem slItem);

    public void remove(SLItem slItem);
}
