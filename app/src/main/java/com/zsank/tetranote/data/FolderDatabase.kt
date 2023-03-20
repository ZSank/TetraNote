package com.zsank.tetranote.data

class FolderDatabase() {
	fun getFolder(): List<Folder> {
		return listOf<Folder>(
			Folder(1, "Note", 0),
			Folder(2, "Work", 1),
			Folder(3, "Shopping", 2),
			Folder(13, "Shopping", 2),
			Folder(25, "Shopping", 13)

		)
	}

}