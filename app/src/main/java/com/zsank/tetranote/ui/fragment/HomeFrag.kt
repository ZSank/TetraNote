package com.zsank.tetranote.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.zsank.tetranote.NoteViewModel
import com.zsank.tetranote.R
import com.zsank.tetranote.data.Folder
import com.zsank.tetranote.data.FolderDatabase
import com.zsank.tetranote.data.Note
import com.zsank.tetranote.databinding.FragmentHomeBinding
import com.zsank.tetranote.ui.folder.FolderAdapter
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "test"
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
	private val navigationArgs: HomeFragArgs by navArgs()
	private var receivedParentFolderId: Int = 0
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
		receivedParentFolderId = navigationArgs.parentFolderId

		val lamda: (Note) -> Unit = { passedNote ->
			val action = HomeFragDirections.actionHomeFragToEditNoteFrag(passedNote.id!!)
			findNavController().navigate(action)
		}
		val noteAdapter = NoteAdapter(lamda)
//			val action = HomeFragDirections.actionHomeFragToEditNoteFrag(it.id!!)
//			view.findNavController().navigate(action)

		binding.rcyViewHome.adapter = noteAdapter
		binding.rcyViewHome.isNestedScrollingEnabled = false
		viewModel.allNotes.observe(this.viewLifecycleOwner) { noteList ->
			noteAdapter.submitList(
				noteList
			)
		}


		val folderData = FolderDatabase().getFolder().filter { it.parent == receivedParentFolderId }
		val reloadFragment: (parentId: Int) -> Unit = { parentId ->
			Log.d(TAG, "reloadFragment: parentId:$parentId ")
			val action = HomeFragDirections.actionHomeFragSelf(parentId)
			findNavController().navigate(action)
		}
		val folderAdapter = FolderAdapter(folderData, reloadFragment)
		binding.rcyViewFolder.isNestedScrollingEnabled = false
		binding.rcyViewFolder.adapter = folderAdapter


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
			R.id.AboutMenu -> {
				navigateToAbout()
				true

			}
			R.id.AddNoteMenu -> {
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

