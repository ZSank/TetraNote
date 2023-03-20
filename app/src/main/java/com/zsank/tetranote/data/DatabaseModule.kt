package com.zsank.roompratice

import android.content.Context
import androidx.room.Room
import com.zsank.tetranote.data.NoteDao
import com.zsank.tetranote.data.NoteDatabase
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
	fun provideDatabase(@ApplicationContext context: Context): NoteDatabase {
		return Room.databaseBuilder(context, NoteDatabase::class.java, "note_database")
			.build()
	}
}
