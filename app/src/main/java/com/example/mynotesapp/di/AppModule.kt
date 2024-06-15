package com.example.mynotesapp.di

import android.app.Application
import androidx.room.Room
import com.example.mynotesapp.data.NoteDao
import com.example.mynotesapp.data.NotesDatabase
import com.example.mynotesapp.viewmodel.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(
        app: Application
    ): NotesDatabase {
        return Room.databaseBuilder(
            app,
            NotesDatabase::class.java,
            NotesDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideNoteDao(db: NotesDatabase): NoteDao {
        return db.notesDao
    }

    @Provides
    @Singleton
    fun provideRepository(Dao: NoteDao): NoteRepository {
        return NoteRepository(Dao)
    }
}