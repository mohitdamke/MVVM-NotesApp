package com.example.mynotesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mynotesapp.data.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {
    fun getAllNotes() = repository.getAllNotes().asLiveData(viewModelScope.coroutineContext)

    fun getUpsertNote(note: Note) {
        viewModelScope.launch {
            repository.upsertNotes(note)
        }
    }

    fun getDeleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNotes(note)
        }
    }

}