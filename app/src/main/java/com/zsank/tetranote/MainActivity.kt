package com.zsank.tetranote

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.zsank.tetranote.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	lateinit var navController: NavController
	//	private lateinit var navController: NavController
	override fun onCreate(savedInstanceState: Bundle?) {
		val splashScreen = installSplashScreen()
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//		val navController = this.findNavController(R.id.fragmentContainerView)
//		setupActionBarWithNavController(navController)
	}
//		override fun onNavigateUp(): Boolean {
//			return navController.navigateUp() || super.onNavigateUp()
//		}
//	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//		val inflater: MenuInflater = menuInflater
//		inflater.inflate(R.menu.editscrn, menu)
//		return true
//	}
}
