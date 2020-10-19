package com.example.madlevel4task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.Adapter.HistoryAdapter
import com.example.madlevel4task2.Model.Game
import com.example.madlevel4task2.Repository.RPSRepository
import com.example.madlevel4task2.databinding.FragmentGamesHistoryBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class GamesHistoryFragment : Fragment() {

    private val games = arrayListOf<Game>()
    private val historyAdapter = HistoryAdapter(games)

    private lateinit var rpsRepository: RPSRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var binding: FragmentGamesHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGamesHistoryBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rpsRepository = RPSRepository(requireContext())
        getGames()

        initRv()

    }

    private fun initRv() {
        viewManager = LinearLayoutManager(activity)
        binding.rvGamesHistory.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )

        binding.rvGamesHistory.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = historyAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete -> {
                deleteGames()
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getGames() {
        mainScope.launch {
            val games = withContext(Dispatchers.IO) {
                rpsRepository.getAllGames()
            }
            this@GamesHistoryFragment.games.clear()
            this@GamesHistoryFragment.games.addAll(games)
            this@GamesHistoryFragment.historyAdapter.notifyDataSetChanged()
        }
    }

    private fun deleteGames(): Boolean {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                rpsRepository.clearGameHistory()
            }
            getGames()
        }
        return true
    }
}