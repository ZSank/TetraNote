package com.zsank.tetranote.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zsank.tetranote.NoteViewModel
import com.zsank.tetranote.R
import com.zsank.tetranote.data.Note
import com.zsank.tetranote.databinding.NoteItemHomeBinding
import timber.log.Timber

class NoteAdapter(
	private val onItemClicked: (Note) -> Unit, private val moreOptionClicked: (View,Note) -> Unit
) :
	ListAdapter<Note, NoteAdapter.ViewHolder>(DiffCallback) {
	
	companion object DiffCallback : DiffUtil.ItemCallback<Note>() {
		override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
			return oldItem.id == newItem.id
		}
		
		override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
			return oldItem.body == newItem.body
		}
	}
	
	class ViewHolder(val binding: NoteItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(item: Note) {
			binding.title.text = item.title
			binding.description.text = item.body
		}
	}
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val binding =
			NoteItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		val holder = ViewHolder(binding)
//		holder.binding.layout.setOnClickListener {
//			val position = holder.adapterPosition
//			val item = getItem(position)
//			onClick(item.id!!)
//		}
		return holder
	}
	
	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val current = getItem(position)
		holder.binding.title.setOnClickListener {
			onItemClicked(current)
		}
		holder.itemView.setOnLongClickListener {
			Timber.d("longClicked")
			true
		}
		holder.binding.noteMoreOption.setOnClickListener {
			moreOptionClicked(it, current)
//			showMenu(it,current)
		}
		holder.bind(current)
	}
	
	private fun showMenu(v: View, note: Note) {
		PopupMenu(v.context, v).apply {
			// MainActivity implements OnMenuItemClickListener
			
			setOnMenuItemClickListener { item ->
				when (item.itemId) {
					R.id.deleteNoteMenu -> {
//						Toast.makeText(v.context, "${note.title}", Toast.LENGTH_SHORT).show()
//						deleteNote(noteId)
						Timber.d("Delete Menu Clicked")
						true
					}
					R.id.shareNoteMenu -> {
						
						Timber.d("Share Menu Clicked")
						true
					}
					else -> false
				}
			}
			inflate(R.menu.editmenu)
			show()
		}
	}
	
}