package com.example.strawpoll

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.work.*
import com.example.strawpoll.databinding.ActivityMainBinding
import com.example.strawpoll.workers.RefreshDataWork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val applicationScope = CoroutineScope(Dispatchers.Default)
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("UNUSED_VARIABLE")
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        drawerLayout = binding.drawerLayout
        val navController = this.findNavController(R.id.navHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

        delayedInit()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    private fun delayedInit() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .setRequiresDeviceIdle(true)
            .build()

        applicationScope.launch {
            val reapeatingReqest = PeriodicWorkRequestBuilder<RefreshDataWork>(
                1,TimeUnit.DAYS
            )
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance() .enqueueUniquePeriodicWork(
                RefreshDataWork.WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                reapeatingReqest
            )
        }
    }
}