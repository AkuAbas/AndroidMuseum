package com.example.practicesoft.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlayerDAO {
    @Insert
    fun insertPlayer(player: Player)

    @Query("select * from player_table")
    suspend fun getPlayer(): List<Player>
    
}