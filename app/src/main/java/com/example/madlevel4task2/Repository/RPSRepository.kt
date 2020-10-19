package com.example.madlevel4task2.Repository

import android.content.Context
import com.example.madlevel4task2.DAO.RPSDao
import com.example.madlevel4task2.Database.RPSDatabase
import com.example.madlevel4task2.Model.Game

class RPSRepository(context: Context) {
    private val rpsDao: RPSDao

    init {
        val database = RPSDatabase.getDatabase(context)
        rpsDao = database!!.rpsDao()
    }

    suspend fun getAllGames(): List<Game> =
        rpsDao.getAllGames()

    suspend fun getAllPlayerWins(): Int =
        rpsDao.getAllPlayerWins()

    suspend fun getAllDraws(): Int =
        rpsDao.getAllDraws()

    suspend fun getAllComputerWins(): Int =
        rpsDao.getAllComputerWins()


    suspend fun insertGame(game: Game) =
        rpsDao.insertGame(game)


    suspend fun clearGameHistory() =
        rpsDao.clearGameHistory()

}