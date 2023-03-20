package com.zsank.tetranote.ui.folder

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zsank.tetranote.data.Folder
import com.zsank.tetranote.databinding.FolderItemHomeBinding

private const val TAG = "Folder"
class FolderAdapter(private val dataSet: List<Folder>, val reloadFragment: (parentId: Int) -> Unit) :
	RecyclerView.Adapter<FolderAdapter.ViewHolder>() {



	class ViewHolder(var binding: FolderItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
		private val textView: TextView = binding.title

		init {
//			textView.setOnClickListener {
//				Log.d(TAG, "ViewHolder: FolderClicked ")
//			}
			// Define click listener for the ViewHolder's View
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val binding =
			FolderItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return ViewHolder(binding)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.binding.title.text = dataSet[position].name
		holder.itemView.setOnClickListener {
			Log.d(TAG, "onBindViewHolder: ${dataSet[position].id} folder clicked")
			reloadFragment(dataSet[position].id!!)
		}
	}

	override fun getItemCount() = dataSet.size

}