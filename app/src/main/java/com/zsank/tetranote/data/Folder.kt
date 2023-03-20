package com.zsank.tetranote.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Folder(
	@PrimaryKey(autoGenerate = true) val id: Int?,
	val name: String?,
	val parent: Int?
)
