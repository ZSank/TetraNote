package com.zsank.tetranote.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zsank.tetranote.data.Note
import com.zsank.tetranote.databinding.NoteItemHomeBinding

class NoteAdapter(private val onItemClicked: (Note) -> Unit) :
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
		holder.itemView.setOnClickListener {
			onItemClicked(current)
		}
		holder.bind(current)
	}
}