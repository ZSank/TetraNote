package com.example.notesapp

import androidx.lifecycle.*
import com.example.notesapp.data.Note
import com.example.notesapp.data.NoteDao
import kotlinx.coroutines.launch

class NoteViewModel(private val noteDao: NoteDao) : ViewModel() {

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

	fun deleteNote(note: Note){
		viewModelScope.launch {
			noteDao.deleteNote(note)
		}
	}

}

class NoteViewModelFactory(private val noteDao: NoteDao) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
			@Suppress("UNCHECKED_CAST")
			return NoteViewModel(noteDao) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}
}