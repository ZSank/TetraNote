package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
//	private lateinit var navController: NavController
	override fun onCreate(savedInstanceState: Bundle?) {
		val splashScreen = installSplashScreen()
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

//		val navController = this.findNavController(R.id.fragmentContainerView)
//		NavigationUI.setupActionBarWithNavController(this, navController)
	}
	//	override fun onNavigateUp(): Boolean {
//		val navController = this.findNavController(R.id.nav_host_fragment)
//		return navController.navigateUp()
//	}
//	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//		val inflater: MenuInflater = menuInflater
//		inflater.inflate(R.menu.editscrn, menu)
//		return true
//	}
}
