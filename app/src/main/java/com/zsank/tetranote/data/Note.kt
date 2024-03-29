package com.zsank.tetranote.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
	@PrimaryKey(autoGenerate = true)
	val id: Int?,
	@ColumnInfo(name = "title")
	val title: String?,
	@ColumnInfo(name = "body")
	val body: String?,
	@ColumnInfo(name = "parent")
	val parent: Int? = null
)