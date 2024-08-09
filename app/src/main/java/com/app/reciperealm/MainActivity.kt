package com.app.reciperealm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.app.reciperealm.databinding.ActivityMainBinding
import com.app.reciperealm.extensions.hide
import com.app.reciperealm.extensions.show

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment_activity_main)

        binding.navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener(this)

    }

    override fun onDestinationChanged(
        controller: NavController, destination: NavDestination, arguments: Bundle?
    ) {
        when (destination.id) {
            R.id.homeFragment -> binding.navView.show()
            R.id.favoriteFragment -> binding.navView.show()
            R.id.notificationFragment -> binding.navView.show()
            R.id.profileFragment -> binding.navView.show()
            else -> binding.navView.hide()
        }
    }
}