package com.example.madlevel4task2.Adapter

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.Model.Enum.Winner
import com.example.madlevel4task2.Model.Game
import com.example.madlevel4task2.R
import com.example.madlevel4task2.databinding.FragmentGamesHistoryBinding
import com.example.madlevel4task2.databinding.FragmentPlayedGameBinding
import com.example.madlevel4task2.getImageResource

class HistoryAdapter (private val games: List<Game>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = FragmentPlayedGameBinding.bind(itemView)

        fun dataBind(game: Game) {
            binding.ivComputer.setImageResource(getImageResource(game.computer))
            binding.ivYou.setImageResource(getImageResource(game.player))
            binding.tvDateTime.text = game.date
            binding.tvResult.text = Winner.values()[game.winner].name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_played_game, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBind(games[position])
    }

    override fun getItemCount(): Int {
        return games.size
    }

}