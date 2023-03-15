package com.zsank.tetranote

import android.app.Application
import com.zsank.tetranote.data.NoteDatabase

class NoteApplication : Application() {
	val database: NoteDatabase by lazy { NoteDatabase.getDatabase(this) }
}