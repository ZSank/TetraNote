package com.example.notesapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.notesapp.NoteApplication
import com.example.notesapp.NoteViewModel
import com.example.notesapp.NoteViewModelFactory
import com.example.notesapp.R
import com.example.notesapp.data.Note
import com.example.notesapp.databinding.FragmentHomeBinding

class HomeFrag : Fragment() {
	private val viewModel: NoteViewModel by activityViewModels {
		NoteViewModelFactory(
			(activity?.application as NoteApplication).database.noteDao()
		)
	}
	private lateinit var  note: Note
	private lateinit var binding: FragmentHomeBinding
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)


		val lamda : (Note)-> Unit = { passedNote ->
			val action = HomeFragDirections.actionHomeFragToEditNoteFrag(passedNote.id!!)
			findNavController().navigate(action)
		}
		val adapter = NoteAdapter(lamda)
//			val action = HomeFragDirections.actionHomeFragToEditNoteFrag(it.id!!)
//			view.findNavController().navigate(action)

		binding.rcyViewHome.adapter = adapter
		viewModel.allNotes.observe(this.viewLifecycleOwner){noteList -> adapter.submitList(noteList)}


		//diffutil not working. Duplicate ids are also added.
		binding.AddNoteFab.setOnClickListener {
			val action = HomeFragDirections.actionHomeFragToCreateNoteFrag()
			findNavController().navigate(action)
		}
	}
}