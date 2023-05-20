package com.zsank.tetranote.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.zsank.tetranote.data.Folder
import com.zsank.tetranote.data.FolderDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FolderViewModel @Inject constructor(private val folderDao: FolderDao) : ViewModel() {

	val allNotes: LiveData<List<Folder>> = folderDao.showAllFolder().asLiveData()
	val count = 50
	fun insertFolder(folder: Folder) {
		viewModelScope.launch {
			folderDao.addFolder(folder)
		}
	}

	fun getFolders(id: Int): LiveData<List<Folder>> {
		return folderDao.showFolders(id).asLiveData()
	}

	fun updateFolder(folder: Folder) {
		viewModelScope.launch {
			folderDao.updateFolder(folder)
		}
	}

	fun deleteFolder(folder: Folder) {
		viewModelScope.launch {
			folderDao.deleteFolder(folder)
		}
	}

}