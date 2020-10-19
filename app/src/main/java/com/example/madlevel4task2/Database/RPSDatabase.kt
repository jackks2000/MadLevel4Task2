package com.example.madlevel4task2.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.madlevel4task2.DAO.RPSDao
import com.example.madlevel4task2.Model.Game

@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class RPSDatabase: RoomDatabase() {
    abstract fun rpsDao(): RPSDao

    companion object {
        private const val DATABASE_NAME = "RPS_DATABASE"

        @Volatile
        private var rpsRoomDatabaseInstance: RPSDatabase? = null

        fun getDatabase(context: Context): RPSDatabase? {
            if (rpsRoomDatabaseInstance == null) {
                synchronized(RPSDatabase::class.java) {
                    if (rpsRoomDatabaseInstance == null) {
                        rpsRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            RPSDatabase::class.java,
                            DATABASE_NAME
                        ).fallbackToDestructiveMigration().build()
                    }
                }
            }
            return rpsRoomDatabaseInstance
        }
    }
}