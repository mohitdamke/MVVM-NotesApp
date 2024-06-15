package com.example.mynotesapp.viewmodel

import com.example.mynotesapp.data.Note
import com.example.mynotesapp.data.NoteDao
import javax.inject.Inject

class NoteRepository @Inject constructor(private val database: NoteDao) {
    suspend fun upsertNotes(note: Note) {
        database.getUpsert(note)
    }

    suspend fun deleteNotes(note: Note) {
        database.getDelete(note)
    }

    fun getAllNotes() = database.getAllNotes()
}