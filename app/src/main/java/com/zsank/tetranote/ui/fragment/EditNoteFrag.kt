package com.zsank.tetranote.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.zsank.tetranote.NoteApplication
import com.zsank.tetranote.NoteViewModel
import com.zsank.tetranote.NoteViewModelFactory
import com.zsank.tetranote.R
import com.zsank.tetranote.data.Note
import com.zsank.tetranote.databinding.FragmentCreateNoteBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "test"

@AndroidEntryPoint
class EditNoteFrag : Fragment() {
	private lateinit var binding: FragmentCreateNoteBinding

	//	private lateinit var binding: FragmentEditNoteBinding
	private val navigationArgs: EditNoteFragArgs by navArgs()
//	private val viewModel: NoteViewModel by activityViewModels {
//		NoteViewModelFactory(
//			(activity?.application as NoteApplication).database.noteDao()
//		)
//	}
	private val viewModel: NoteViewModel by viewModels()
	private var retrievedNote: Note? = null

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		binding =
			DataBindingUtil.inflate(layoutInflater, R.layout.fragment_create_note, container, false)
		binding.deleteFab.hide()
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		//noteId from listFragment
		val receivedId = navigationArgs.NoteId


		viewModel.retrieveNote(receivedId).observe(this.viewLifecycleOwner) {
			retrievedNote = it
			displayNote(it)
		}

		binding.saveFab.setOnClickListener {
			updateNote(retrievedNote!!)
		}
		binding.deleteFab.setOnClickListener {
			deleteNote(retrievedNote!!)
			val action = EditNoteFragDirections.actionEditNoteFragToHomeFrag()
			findNavController().navigate(action)
		}

	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		//		val inflater: MenuInflater = menuInflater
		inflater.inflate(R.menu.editscrn, menu)
//		return true
		super.onCreateOptionsMenu(menu, inflater)
	}
//	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//		super.onCreateOptionsMenu(menu, inflater)
//		inflater.inflate(R.menu.editscrn,menu)
//	}

	private fun updateNote(retrievedNote: Note) {
		val updatedNote = Note(
			retrievedNote.id,
			binding.edtTitle.text.toString(),
			binding.edtBody.text.toString()
		)
		viewModel.updateNote(updatedNote)
	}

	private fun displayNote(note: Note) {
		binding.apply {
			edtTitle.setText(note.title)
			edtBody.setText(note.body)
		}
	}

	private fun deleteNote(note: Note) {
		viewModel.deleteNote(note)
	}


}