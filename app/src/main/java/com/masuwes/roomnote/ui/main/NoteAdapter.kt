package com.masuwes.roomnote.ui.main

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.masuwes.roomnote.R
import com.masuwes.roomnote.database.Note
import com.masuwes.roomnote.helper.NoteDiffCallback
import com.masuwes.roomnote.ui.insert.NoteAddUpdateActivity
import com.masuwes.roomnote.ui.insert.NoteAddUpdateActivity.Companion.EXTRA_NOTE
import com.masuwes.roomnote.ui.insert.NoteAddUpdateActivity.Companion.EXTRA_POSITION
import com.masuwes.roomnote.ui.insert.NoteAddUpdateActivity.Companion.REQUEST_UPDATE
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter internal constructor(private val activity: Activity) : RecyclerView.Adapter<NoteAdapter.ViewHolder>(){

    private val listNotes = ArrayList<Note>()

    fun setListNotes(list: List<Note>) {
        val diffCallback = NoteDiffCallback(listNotes, list)
        val diffResults = DiffUtil.calculateDiff(diffCallback)
        listNotes.clear()
        listNotes.addAll(list)
        diffResults.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }

    override fun getItemCount(): Int = listNotes.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(note: Note) {
            with(itemView) {
                tv_item_title.text = note.title
                tv_item_description.text = note.description
                tv_item_date.text = note.date
                cv_item_note.setOnClickListener {
                    val intent = Intent(activity, NoteAddUpdateActivity::class.java)
                    intent.putExtra(EXTRA_POSITION, adapterPosition)
                    intent.putExtra(EXTRA_NOTE, note)
                    activity.startActivityForResult(intent, REQUEST_UPDATE)
                }
            }
        }
    }
}