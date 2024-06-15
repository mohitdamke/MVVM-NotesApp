package com.example.mynotesapp.domain

import com.example.mynotesapp.data.Note
import com.example.mynotesapp.data.NoteDao
import javax.inject.Inject

class NotesRepository @Inject constructor(
    private val notesDao: NoteDao
) {

    suspend fun getUpsert(note: Note) {
        notesDao.getUpsert(note)
    }

    suspend fun getDelete(note: Note) {
        notesDao.getDelete(note)
    }

    fun getAllNotes() = notesDao.getAllNotes()

}