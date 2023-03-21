package com.zsank.tetranote.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface FolderDao {

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun addFolder(folder: Folder)

	@Update
	suspend fun updateFolder(folder: Folder)

	@Delete
	suspend fun deleteFolder(folder: Folder)


	@Query("select * from folder")
	fun showAllFolder(): Flow<List<Folder>>

	@Query("select * from folder where parent = :parentId")
	fun showFolders(parentId: Int): Flow<List<Folder>>

	@Query("select * from folder where id = :id")
	suspend fun getSingleFolder(id: Int): Folder

}