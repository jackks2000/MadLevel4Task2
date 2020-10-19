package com.example.madlevel4task2.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.madlevel4task2.Model.Game

@Dao
interface RPSDao {
    @Query("SELECT * FROM game")
    suspend fun getAllGames(): List<Game>

    @Query("SELECT COUNT(*) FROM game WHERE winner = 0")
    suspend fun getAllPlayerWins(): Int

    @Query("SELECT COUNT(*) FROM game WHERE winner = 1")
    suspend fun getAllDraws(): Int

    @Query("SELECT COUNT(*) FROM game WHERE winner = 2")
    suspend fun getAllComputerWins(): Int

    @Insert
    suspend fun insertGame(game: Game)

    @Query("DELETE FROM game")
    suspend fun clearGameHistory()
}