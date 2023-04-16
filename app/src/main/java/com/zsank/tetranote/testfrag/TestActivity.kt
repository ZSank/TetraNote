package com.zsank.tetranote.testfrag

import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.zsank.tetranote.R
import com.zsank.tetranote.databinding.ActivityTestBinding
import timber.log.Timber

class TestActivity : AppCompatActivity() {
	private lateinit var binding: ActivityTestBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this, R.layout.activity_test)
//		setSupportActionBar(binding.materialToolbar)

//		val navHostFragment =
//			supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
//		val navController = navHostFragment.navController
//		val appBarConfiguration = AppBarConfiguration(navController.graph)
//		setupActionBarWithNavController(navController, appBarConfiguration)
		
		
	}
	
}