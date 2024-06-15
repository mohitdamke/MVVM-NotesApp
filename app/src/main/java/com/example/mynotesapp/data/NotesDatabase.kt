package com.example.mynotesapp.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Note::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract val notesDao: NoteDao

    companion object{
        const val DATABASE_NAME = "notes_db"
    }

}