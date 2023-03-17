package com.zsank.tetranote

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NoteApplication : Application() {
//	val database: NoteDatabase by lazy { NoteDatabase.getDatabase(this) }
}