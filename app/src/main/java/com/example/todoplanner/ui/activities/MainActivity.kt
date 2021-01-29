package com.example.todoplanner.ui.activities

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.todoplanner.R
import com.example.todoplanner.databinding.ActivityMainBinding
import com.example.todoplanner.framework.DayPlannerService
import com.example.todoplanner.ui.viewmodels.TodoViewModel
import com.example.todoplanner.ui.viewmodels.factories.TodoViewModelFactory
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var viewModel: TodoViewModel
    val servConn: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as DayPlannerService.DayPlannerBinder
            viewModel = ViewModelProvider(this@MainActivity, TodoViewModelFactory(application, binder)).get(TodoViewModel::class.java)
        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navviewMain.setNavigationItemSelectedListener(this)


        val appBarConfiguration = AppBarConfiguration(findNavController(R.id.frame_main).graph, binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.frame_main) as NavHostFragment
        val navController = navHostFragment.navController
        binding.navviewMain.setupWithNavController(navController)
        binding.toolbarMain.setupWithNavController(navController, appBarConfiguration)

        startService(Intent(this, DayPlannerService::class.java))
        bindService(Intent(this, DayPlannerService::class.java), servConn, Context.BIND_AUTO_CREATE)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.frame_main)
        return navController.navigateUp()
                || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.frame_main)
        return item.onNavDestinationSelected(navController)
    }
}