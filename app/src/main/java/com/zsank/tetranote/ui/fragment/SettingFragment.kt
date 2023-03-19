package com.zsank.tetranote.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.zsank.tetranote.R
import com.zsank.tetranote.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

private lateinit var binding: FragmentSettingBinding
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_setting, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.themeSwitch.setOnCheckedChangeListener { _, isChecked ->
			if (isChecked) {
				// Set dark mode theme
				AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
				saveThemeSelection(true)
			} else {
				// Set light mode theme
				AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
				saveThemeSelection(false)
			}
		}

	}

	private fun saveThemeSelection(isDarkMode: Boolean) {
		val sharedPreferences = requireContext().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
		sharedPreferences.edit().putBoolean("isDarkMode", isDarkMode).apply()
	}



}