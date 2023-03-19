package com.zsank.tetranote

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NoteApplication : Application() {
	//	val database: NoteDatabase by lazy { NoteDatabase.getDatabase(this) }
	override fun onCreate() {
		super.onCreate()

		val sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
		val isDarkMode = sharedPreferences.getBoolean("isDarkMode", false)
		if (isDarkMode) {
			AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
		} else {
			AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
		}

	}
}