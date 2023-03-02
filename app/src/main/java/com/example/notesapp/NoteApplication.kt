package com.example.notesapp

import android.app.Application
import com.example.notesapp.data.NoteDatabase

class NoteApplication: Application() {
	val database: NoteDatabase by lazy { NoteDatabase.getDatabase(this) }
}