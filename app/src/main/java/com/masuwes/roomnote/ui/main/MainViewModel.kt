package com.masuwes.roomnote.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.google.android.material.snackbar.Snackbar
import com.masuwes.roomnote.database.Note
import com.masuwes.roomnote.repository.NoteRepository

class MainViewModel(application: Application) : ViewModel() {
    private val mNoteRepository: NoteRepository = NoteRepository(application)

    fun getAllNotes(sort: String): LiveData<PagedList<Note>> {
        return LivePagedListBuilder(mNoteRepository.getAllNotes(sort), 20).build()
    }
}