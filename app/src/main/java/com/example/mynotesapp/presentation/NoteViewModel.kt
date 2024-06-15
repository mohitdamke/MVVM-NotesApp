package com.example.mynotesapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mynotesapp.data.Note
import com.example.mynotesapp.domain.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NotesRepository) : ViewModel() {

    fun getAllNotes() = repository.getAllNotes().asLiveData(viewModelScope.coroutineContext)

    fun getUpsert(note: Note) {
        viewModelScope.launch {
            repository.getUpsert(note)
        }
    }

    fun getDelete(note: Note) {
        viewModelScope.launch {
            repository.getDelete(note)
        }
    }


}