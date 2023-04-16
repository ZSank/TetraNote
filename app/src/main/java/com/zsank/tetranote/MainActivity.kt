package com.zsank.tetranote

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.zsank.tetranote.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	private lateinit var childFragManager: FragmentManager
	override fun onCreate(savedInstanceState: Bundle?) {
		val splashScreen = installSplashScreen()
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
		setSupportActionBar(binding.topAppBar)
		supportActionBar?.setDisplayHomeAsUpEnabled(false)
		
		//Toolbar as Appbar
		val navHostFragment =
			supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
		val navController = navHostFragment.navController
		val appBarConfiguration = AppBarConfiguration(setOf())
//		val appBarConfiguration = AppBarConfiguration(navController.graph)
//		val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFrag))
		// TODO: hide back button in TopAppBar in root folder
		findViewById<Toolbar>(R.id.topAppBar)
			.setupWithNavController(navController, appBarConfiguration)
		
		childFragManager = navHostFragment.childFragmentManager
		
		supportActionBar?.setHomeAsUpIndicator(0) //Changes back icon to default for first time
		
		childFragManager.addOnBackStackChangedListener {
			showHideUpButton()
		}
		supportActionBar?.setDisplayHomeAsUpEnabled(false)
	}
	
	private fun showHideUpButton() {
		val count = childFragManager.backStackEntryCount
		supportActionBar?.setDisplayHomeAsUpEnabled(count > 0)
	}
//	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//		val inflater: MenuInflater = menuInflater
//		inflater.inflate(R.menu.menu_note_home, menu)
//		return true
//	}

}
