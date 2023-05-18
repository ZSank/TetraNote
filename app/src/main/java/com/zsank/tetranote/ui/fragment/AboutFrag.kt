package com.zsank.tetranote.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.zsank.tetranote.R
import com.zsank.tetranote.databinding.FragmentAboutBinding

class AboutFrag : Fragment() {
	private lateinit var binding: FragmentAboutBinding
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_about, container, false)
		// Inflate the layout for this fragment
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		binding.AboutGithub.setOnClickListener {
			val webIntent: Intent = Intent(
				Intent.ACTION_VIEW,
				Uri.parse("https://www.github.com/zsank")
			)
			startActivity(webIntent)
		}
		
		binding.AboutLinkedIn.setOnClickListener {
			val webIntent: Intent = Intent(
				Intent.ACTION_VIEW,
				Uri.parse("https://www.linkedin.com/in/sanketzade")
			)
			startActivity(webIntent)
		}
	}

}