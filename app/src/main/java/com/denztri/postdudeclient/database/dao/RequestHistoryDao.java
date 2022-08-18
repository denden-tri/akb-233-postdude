package com.denztri.postdudeclient.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.denztri.postdudeclient.database.entity.RequestHistoryModel;

import java.util.List;

@Dao
public interface RequestHistoryDao {
    @Query("SELECT * FROM RequestHistoryModel ORDER BY id DESC")
    List<RequestHistoryModel> getAllHistory();

    @Insert
    void insertOne(RequestHistoryModel requestHistoryModel);

    @Query("DELETE FROM requesthistorymodel")
    void deleteAll();

    @Query("SELECT COUNT(id) FROM requesthistorymodel")
    int checkDb();
}
