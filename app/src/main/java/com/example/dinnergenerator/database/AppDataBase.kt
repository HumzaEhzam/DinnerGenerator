package com.example.dinnergenerator.database

import androidx.room.RoomDatabase

//@Database(entities = [Attendance::class,Location::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun dbDao(): AppDao
}