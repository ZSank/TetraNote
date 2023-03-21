package com.zsank.tetranote.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

	@Provides
	fun provideNoteDao(noteDatabase: NoteDatabase): NoteDao {
		return noteDatabase.noteDao()
	}

	@Provides
	@Singleton
	fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase {
		return Room.databaseBuilder(context, NoteDatabase::class.java, "note_database")
			.build()
	}

	@Provides
	fun provideFolderDao(folderDatabase: FolderDatabase): FolderDao {
		return folderDatabase.folderDao()
	}

	@Provides
	@Singleton
	fun provideFolderDatabase(@ApplicationContext context: Context): FolderDatabase {
		return Room.databaseBuilder(context, FolderDatabase::class.java, "folder_database")
			.build()
	}
}
