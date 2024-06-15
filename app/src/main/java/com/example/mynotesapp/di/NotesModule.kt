package com.example.mynotesapp.di

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.mynotesapp.data.Note
import com.example.mynotesapp.data.NoteDao
import com.example.mynotesapp.data.NotesDatabase
import com.example.mynotesapp.domain.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotesModule {

    @Provides
    @Singleton
    fun provideNotesDatabase(application: Application): NotesDatabase {
        return Room.databaseBuilder(
            application, NotesDatabase::class.java, NotesDatabase.DATABASE_NAME
        )
            .addMigrations(NotesDatabase.Migration1to2)
            .build()
    }

    @Provides
    @Singleton
    fun provideNotesDao(noteDatabase: NotesDatabase): NoteDao = noteDatabase.noteDao


    @Provides
    @Singleton
    fun provideNotesRepository(notesDao: NoteDao): NotesRepository = NotesRepository(notesDao)

}