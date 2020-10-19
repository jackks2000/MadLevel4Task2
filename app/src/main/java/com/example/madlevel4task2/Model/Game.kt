package com.example.madlevel4task2.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class Game (
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,

    var computer: Int,

    var player: Int,

    var winner: Int,

    var date: String
)