package com.example.practicesoft.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("player_table")
data class Player(
    @ColumnInfo("player_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    @ColumnInfo("player_name")
    val name: String,
    @ColumnInfo("player_team")
    val team: String
)
