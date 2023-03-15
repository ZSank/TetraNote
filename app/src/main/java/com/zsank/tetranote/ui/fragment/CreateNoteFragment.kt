package com.zsank.tetranote.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.zsank.tetranote.NoteApplication
import com.zsank.tetranote.NoteViewModel
import com.zsank.tetranote.NoteViewModelFactory
import com.zsank.tetranote.R
import com.zsank.tetranote.data.Note
import com.zsank.tetranote.databinding.FragmentCreateNoteBinding


private const val TAG = "note"

class CreateNoteFragment : Fragment() {
	private val viewModel: NoteViewModel by activityViewModels {
		NoteViewModelFactory(
			(activity?.application as NoteApplication).database.noteDao()
		)
	}
	private lateinit var note: Note
	private lateinit var binding: FragmentCreateNoteBinding
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
			val action = CreateNoteFragmentDirections.actionCreateNoteFragToHomeFrag()
			findNavController().navigate(action)
		}

	}

	private fun saveNote() {

		binding.apply {
			val note = Note(null, edtTitle.text.toString(), edtBody.text.toString())
			if (!note.title.isNullOrEmpty() or !note.body.isNullOrEmpty()) {
				viewModel.insertNote(note)
			} else {
				Toast.makeText(requireContext(), "Not saved Empty", Toast.LENGTH_SHORT).show()
			}
		}
	}
}