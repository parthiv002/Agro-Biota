package com.example.sih_app_demo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sih_app_demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_market
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Set the logo on the far left of the ActionBar
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true) // Enable the logo (home) icon
            setLogo(R.drawable.logo_coloured_small)  // Replace with your logo image name
            setDisplayUseLogoEnabled(true) // Show the logo
            setDisplayShowTitleEnabled(true)  // Ensure the title is displayed next to the logo
            title = "Agrobiota"  // Set the title text next to the logo
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                // Handle Settings action
                true
            }
            R.id.action_profile -> {
                // Handle Profile action
                true
            }
            R.id.action_help -> {
                // Handle Help action
                true
            }
            R.id.action_alter_theme -> {
                // Handle Alter Theme action
                true
            }
            R.id.action_report_issue -> {
                // Handle Report Issue action
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
