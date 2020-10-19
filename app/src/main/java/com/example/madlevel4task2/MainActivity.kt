package com.example.madlevel4task2

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.madlevel4task2.databinding.ActivityMainBinding
import com.example.madlevel4task2.databinding.ActivityMainBinding.inflate
import com.example.madlevel4task2.databinding.ContentMainBinding.inflate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    private var menu = R.menu.menu_history


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))

        toolbar.setNavigationOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }

        navController = findNavController(R.id.nav_host_fragment)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(this.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.history -> {
                item.isVisible = false
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                supportActionBar!!.setDisplayShowHomeEnabled(true)
                menu = R.menu.menu_delete
                onCreateOptionsMenu(toolbar.menu)
                navController.navigate(
                    R.id.action_PlayFragment_to_GamesHistoryFragment)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}