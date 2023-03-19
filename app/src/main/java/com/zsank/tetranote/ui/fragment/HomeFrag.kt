package com.zsank.tetranote.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.zsank.tetranote.NoteApplication
import com.zsank.tetranote.NoteViewModel
import com.zsank.tetranote.NoteViewModelFactory
import com.zsank.tetranote.R
import com.zsank.tetranote.data.Note
import com.zsank.tetranote.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFrag : Fragment() {
//	private val viewModel: NoteViewModel by activityViewModels {
//		NoteViewModelFactory(
//			(activity?.application as NoteApplication).database.noteDao()
//		)
//	}

	private val viewModel: NoteViewModel by viewModels()
	private lateinit var note: Note
	private lateinit var binding: FragmentHomeBinding
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
		setHasOptionsMenu(true)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)


		val lamda: (Note) -> Unit = { passedNote ->
			val action = HomeFragDirections.actionHomeFragToEditNoteFrag(passedNote.id!!)
			findNavController().navigate(action)
		}
		val adapter = NoteAdapter(lamda)
//			val action = HomeFragDirections.actionHomeFragToEditNoteFrag(it.id!!)
//			view.findNavController().navigate(action)

		binding.rcyViewHome.adapter = adapter
		viewModel.allNotes.observe(this.viewLifecycleOwner) { noteList ->
			adapter.submitList(
				noteList
			)
		}

		//diffutil not working. Duplicate ids are also added.
		binding.AddNoteFab.setOnClickListener {
			addNote()
		}

	}

	private fun addNote() {
		val action = HomeFragDirections.actionHomeFragToCreateNoteFrag()
		findNavController().navigate(action)
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		super.onCreateOptionsMenu(menu, inflater)
		inflater.inflate(R.menu.menu_note_home, menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		return when (item.itemId) {
			R.id.AboutMenu-> {
				navigateToAbout()
				true

			}
			R.id.AddNoteMenu-> {
				addNote()
				true
			}
			else -> super.onOptionsItemSelected(item)
		}
	}

	private fun navigateToAbout() {
		val action = HomeFragDirections.actionHomeFragToAboutFrag()
		findNavController().navigate(action)
	}
}

