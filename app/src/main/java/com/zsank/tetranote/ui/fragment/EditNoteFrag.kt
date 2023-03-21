package com.zsank.tetranote.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
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

private const val TAG = "test"

@AndroidEntryPoint
class EditNoteFrag : Fragment() {
	private lateinit var binding: FragmentCreateNoteBinding
	private lateinit var updatedNote: Note
	//	private lateinit var binding: FragmentEditNoteBinding
	private val navigationArgs: EditNoteFragArgs by navArgs()

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
		setHasOptionsMenu(true)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		//noteId from listFragment
		val receivedId = navigationArgs.NoteId


		viewModel.retrieveNote(receivedId).observe(this.viewLifecycleOwner) {
			retrievedNote = it
			if(binding.edtBody.text.isEmpty()) displayNote(it) //To fill the views first time, with retrievedNote
		}

//		displayNote(retrievedNote!!)
		binding.saveFab.setOnClickListener {
			updateNote(retrievedNote!!.id)
		}
		binding.deleteFab.setOnClickListener {
			deleteNote(retrievedNote!!.id)
			navigateBackToHome()
		}

	}

	private fun navigateBackToHome() {
		val action = EditNoteFragDirections.actionEditNoteFragToHomeFrag()
		findNavController().navigate(action)
	}

	//Called when saved button is pressed. This updates the note in Database.
	private fun updateNote(retrievedNoteId: Int?) {
		updatedNote = Note(
			retrievedNoteId,
			binding.edtTitle.text.toString(),
			binding.edtBody.text.toString()
		)
		viewModel.updateNote(updatedNote)
	}

	//displaying Note on screen
	private fun displayNote(note: Note) {
		binding.apply {
			edtTitle.setText(note.title)
			edtBody.setText(note.body)
		}
	}

	//For delete note, only id is needed. Body is not checked while deleting
	private fun deleteNote(retrievedNoteId: Int?) {
		viewModel.deleteNote(Note(retrievedNoteId,null, null))
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		super.onCreateOptionsMenu(menu, inflater)
		inflater.inflate(R.menu.editmenu, menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		return when (item.itemId) {
			R.id.deleteNoteMenu -> {
				deleteNote(retrievedNote!!.id)
				navigateBackToHome()
				true

			}
			R.id.shareNoteMenu -> {
				shareNote()
				true
			}
			else -> super.onOptionsItemSelected(item)
		}
	}

	private fun shareNote() {
		val shareIntent = Intent(Intent.ACTION_SEND)
		shareIntent.type = "text/plain"
		shareIntent.putExtra(Intent.EXTRA_TEXT, "${binding.edtTitle.text}\n\n${binding.edtBody.text}")
		startActivity(Intent.createChooser(shareIntent, "Share note"))
	}

}

