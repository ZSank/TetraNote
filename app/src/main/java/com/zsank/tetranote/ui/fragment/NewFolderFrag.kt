package com.zsank.tetranote.ui.fragment

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.zsank.tetranote.viewmodel.FolderViewModel
import com.zsank.tetranote.R
import com.zsank.tetranote.data.Folder
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "dialog"

@AndroidEntryPoint
class NewFolderFrag : DialogFragment() {
	private val folderViewModel: FolderViewModel by viewModels()
	private val args: NewFolderFragArgs by navArgs()

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		return activity?.let {
			// Use the Builder class for convenient dialog construction
			val builder = AlertDialog.Builder(it)
			val inflater = requireActivity().layoutInflater
			val layout = inflater.inflate(R.layout.fragment_new_folder, null)
			builder.setView(layout)
			val folderName = layout.findViewById<EditText>(R.id.newFolderName).text

			builder.setMessage("Create New Folder")
				.setPositiveButton("CREATE",
					DialogInterface.OnClickListener { dialog, id ->
						Log.d(TAG, "onCreateDialog: $folderName")
						addFolder(folderName.toString())
					})
				.setNegativeButton("CANCEL",
					DialogInterface.OnClickListener { dialog, id ->
						// User cancelled the dialog
					})
			// Create the AlertDialog object and return it
			builder.create()
		} ?: throw IllegalStateException("Activity cannot be null")
	}

	private fun addFolder(folderName: String) {
		//TODO: Create addFolder Interface interface. Maybe the interface fragment should have logic regarding room , navgraph etc.
		folderViewModel.insertFolder(Folder(null, folderName, args.parentFolder))
	}


}