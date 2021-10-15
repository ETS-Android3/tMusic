package com.adropofliquid.tmusic.queue.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.adropofliquid.tmusic.items.LastPlayedStateItem;

import java.util.List;

@Dao
public interface LastPlatedStateDao {

    @Query("SELECT * FROM lastplayedstateitem LIMIT 1")
    LastPlayedStateItem getLastPlayed();

    @Insert
    void insertAll(LastPlayedStateItem... lastPlayedStateItems);

    @Query("DELETE FROM lastplayedstateitem")
    void delete();

    @Query("SELECT shuffled FROM lastplayedstateitem LIMIT 1")
    boolean getShufflingMode();
}
