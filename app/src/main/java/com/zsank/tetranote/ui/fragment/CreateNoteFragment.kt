package com.zsank.tetranote.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.zsank.tetranote.NoteViewModel
import com.zsank.tetranote.R
import com.zsank.tetranote.data.Note
import com.zsank.tetranote.databinding.FragmentCreateNoteBinding
import dagger.hilt.android.AndroidEntryPoint


private const val TAG = "createnote"

@AndroidEntryPoint
class CreateNoteFragment : Fragment() {
	private val viewModel: NoteViewModel by viewModels()
	private lateinit var note: Note
	private lateinit var binding: FragmentCreateNoteBinding
	private val args: CreateNoteFragmentArgs by navArgs()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding =
			DataBindingUtil.inflate(layoutInflater, R.layout.fragment_create_note, container, false)
		binding.deleteFab.hide() //TODO: Remove deleteFab.hide() from create and edit frag, when menu is used
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.saveFab.setOnClickListener {
			saveNote()
			findNavController().navigateUp()
			//navigateUp instead of action is used, since action doesn't respect the Homefrag of folder and jumps to parentID=0
		}

	}

	private fun saveNote() {

		binding.apply {
			val note = Note(
				null,
				edtTitle.text.toString(),
				edtBody.text.toString(),
				args.parentIdForNote
			)
			if (!note.title.isNullOrEmpty() or !note.body.isNullOrEmpty()) {
				viewModel.insertNote(note)
			} else {
				Toast.makeText(requireContext(), "Not saved \n Empty Note", Toast.LENGTH_SHORT)
					.show()
			}
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		saveNote()
	}

}