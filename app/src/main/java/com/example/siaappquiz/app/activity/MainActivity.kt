package com.example.siaappquiz.app.activity

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.digisapplication.Network.utils.add
import com.example.digisapplication.Network.utils.remove
import com.example.siaappquiz.R
import com.example.siaappquiz.network.NetworkState
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : AppCompatActivity() , NetworkState.NetworkStateReceiverListener{

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var networkState: NetworkState? = null
    var no_internet: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

       // val fab: FloatingActionButton = findViewById(R.id.fab)
      /*  fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

         no_internet = findViewById(R.id.no_internet_message)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home,
            R.id.nav_gallery,
            R.id.nav_slideshow
        ), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        //Network state
        networkState = NetworkState()
        networkState!!.addListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }



    override fun onPause() {
        super.onPause()
        try {
            unregisterReceiver(networkState)
        } catch (e: Exception) {
            Log.e("AllDebug:NetworkState", "Error:${e.message}")
        }
    }
        override fun onStart() {
            super.onStart()
            registerReceiver(networkState, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }

    override fun networkAvailable() {
        no_internet.remove()
        Log.d("network Status", "network not available")
       // Toast.makeText(this, "is avaliable", Toast.LENGTH_SHORT).show()
    }

    override fun networkUnavailable() {
        no_internet?.add()
        Log.d("network Status","network available")
       // Toast.makeText(this, "Not avaliable", Toast.LENGTH_SHORT).show()
    }

}