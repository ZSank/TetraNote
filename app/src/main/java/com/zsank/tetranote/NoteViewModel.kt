package com.zsank.tetranote

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.zsank.tetranote.data.Note
import com.zsank.tetranote.data.NoteDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteDao: NoteDao) : ViewModel() {

	val allNotes: LiveData<List<Note>> = noteDao.showAllNote().asLiveData()
	val count = 50
	fun insertNote(note: Note) {
		viewModelScope.launch {
			noteDao.addNote(note)
		}
	}

	fun retrieveNote(id: Int): LiveData<Note> {
		return noteDao.showNote(id).asLiveData()
	}

	fun updateNote(note: Note) {
		viewModelScope.launch {
			noteDao.updateNote(note)
		}
	}

	fun deleteNote(note: Note) {
		viewModelScope.launch {
			noteDao.deleteNote(note)
		}
	}

}

