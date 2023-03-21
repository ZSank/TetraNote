package com.zsank.tetranote.ui.folder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zsank.tetranote.data.Folder
import com.zsank.tetranote.databinding.FolderItemHomeBinding

private const val TAG = "Folder"

class FolderAdapter(val reloadFragment: (parentId: Int) -> Unit) :
	ListAdapter<Folder, FolderAdapter.ViewHolder>(DiffCallback) {

	companion object DiffCallback : DiffUtil.ItemCallback<Folder>() {
		override fun areItemsTheSame(oldItem: Folder, newItem: Folder): Boolean {
			return oldItem.id == newItem.id
		}

		override fun areContentsTheSame(oldItem: Folder, newItem: Folder): Boolean {
			return false
		}
	}

	class ViewHolder(var binding: FolderItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(item: Folder) {
			binding.title.text = item.name
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val binding =
			FolderItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return ViewHolder(binding)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val current = getItem(position)
		holder.itemView.setOnClickListener {
			reloadFragment(current.id!!)
		}
		holder.bind(current)
	}
}
//class FolderAdapter(private val dataSet: List<Folder>, val reloadFragment: (parentId: Int) -> Unit) :
//	RecyclerView.Adapter<FolderAdapter.ViewHolder>() {
//
//
//
//	class ViewHolder(var binding: FolderItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
//		private val textView: TextView = binding.title
//
//		init {
////			textView.setOnClickListener {
////				Log.d(TAG, "ViewHolder: FolderClicked ")
////			}
//			// Define click listener for the ViewHolder's View
//		}
//	}
//
//	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//		val binding =
//			FolderItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//		return ViewHolder(binding)
//	}
//
//	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//		holder.binding.title.text = dataSet[position].name
//		holder.itemView.setOnClickListener {
//			Log.d(TAG, "onBindViewHolder: ${dataSet[position].id} folder clicked")
//			reloadFragment(dataSet[position].id!!)
//		}
//	}
//
//	override fun getItemCount() = dataSet.size
//
//}