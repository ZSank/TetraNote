package com.zsank.tetranote.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun addNote(note: Note)

	@Update
	suspend fun updateNote(note: Note)

	@Delete
	suspend fun deleteNote(note: Note)


	@Query("select * from note")
	fun showAllNote(): Flow<List<Note>>

	@Query("select * from note where id = :id")
	fun showNote(id: Int): Flow<Note>

	@Query("select * from note where id = :id")
	suspend fun getSingleNote(id: Int): Note

	@Query("select * from note where parent = :parentId")
	fun allNoteInFolder(parentId: Int): Flow<List<Note>>

}