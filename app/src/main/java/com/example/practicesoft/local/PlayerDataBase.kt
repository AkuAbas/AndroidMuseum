package com.example.practicesoft.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Player::class], version = 1)
abstract class PlayerDataBase : RoomDatabase() {

    abstract fun getDao(): PlayerDAO

}