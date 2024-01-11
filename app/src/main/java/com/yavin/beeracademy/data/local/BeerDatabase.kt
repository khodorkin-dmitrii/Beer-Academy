package com.yavin.beeracademy.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BeerEntity::class],
    version = 2,
    exportSchema = false
)
abstract class BeerDatabase: RoomDatabase() {

    abstract val dao: BeerDao
}