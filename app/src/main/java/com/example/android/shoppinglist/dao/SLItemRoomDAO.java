package com.example.android.shoppinglist.dao;

import com.example.android.shoppinglist.model.SLItem;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SLItemRoomDAO {

    @Query("SELECT * FROM slitem")
    public List<SLItem> getAll();

    @Query("SELECT * FROM slitem WHERE id = :id")
    public SLItem findById(long id);

    @Insert
    public void insert(SLItem slItem);

    @Update
    public void update(SLItem slItem);

    @Delete
    public void delete(SLItem slItem);
}
