/*
 * Designed and developed by 2020 keelim (Jaehyun Kim)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.keelim.nandadiagnosis.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.keelim.SettingActivity
import com.keelim.nandadiagnosis.R
import com.keelim.nandadiagnosis.data.repository.theme.AppTheme
import com.keelim.nandadiagnosis.databinding.FragmentMainBottomBinding
import com.keelim.nandadiagnosis.utils.MaterialDialog
import com.keelim.nandadiagnosis.utils.MaterialDialog.Companion.negativeButton
import com.keelim.nandadiagnosis.utils.MaterialDialog.Companion.positiveButton
import com.keelim.nandadiagnosis.utils.MaterialDialog.Companion.singleChoiceItems
import com.keelim.nandadiagnosis.utils.MaterialDialog.Companion.title
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainBottomFragment : BottomSheetDialogFragment() {
  private var _binding: FragmentMainBottomBinding? = null
  private val binding get() = _binding!!

  private val mainViewModel: MainViewModel by activityViewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentMainBottomBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initAppThemeObserver()
    setClickListeners()
  }

  private fun initAppThemeObserver() {
    mainViewModel.theme.observe(
      viewLifecycleOwner
    ) { currentTheme ->
      val appTheme = AppTheme.THEME_ARRAY.firstOrNull { it.modeNight == currentTheme }
      appTheme?.let {
        binding.themeIcon.setImageResource(it.themeIconRes)
        binding.themeDescription.text = getString(it.modeNameRes)
      }
    }
  }

  private fun setClickListeners() {
    binding.themeOption.setOnClickListener {
      chooseThemeClick()
    }

    binding.aboutButton.setOnClickListener {
      dismiss()
      mainViewModel.loadingOn()
      findNavController().navigate(R.id.aboutFragment)
    }

    binding.openSourceLicensesButton.setOnClickListener {
      dismiss()
      mainViewModel.loadingOn()
      findNavController().navigate(R.id.openSource)
    }

    binding.update.setOnClickListener {
      dismiss()
      mainViewModel.loadingOn()
      Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(getString(R.string.urinanda))
        startActivity(this)
      }
    }

    binding.blog.setOnClickListener {
      dismiss()
      mainViewModel.loadingOn()
      findNavController().navigate(R.id.inAppWebFragment)
    }

    binding.login.setOnClickListener {
      dismiss()
      mainViewModel.loadingOn()
      findNavController().navigate(R.id.profileFragment)
    }

    binding.labFeature.setOnClickListener {
      dismiss()
      mainViewModel.loadingOn()
      requireContext().startActivity(Intent(requireContext(), SettingActivity::class.java))
    }
  }

  private fun chooseThemeClick() {
    val currentTheme = mainViewModel.theme.value
    var checkedItem = AppTheme.THEME_ARRAY.indexOfFirst { it.modeNight == currentTheme }
    if (checkedItem >= 0) {
      val items = AppTheme.THEME_ARRAY.map {
        getText(it.modeNameRes)
      }.toTypedArray()
      MaterialDialog.createDialog(requireContext()) {
        title(R.string.choose_theme)
        singleChoiceItems(items, checkedItem) {
          checkedItem = it
        }
        positiveButton(getString(R.string.ok)) {
          val mode = AppTheme.THEME_ARRAY[checkedItem].modeNight
          AppCompatDelegate.setDefaultNightMode(mode)
          mainViewModel.setAppTheme(mode)
          // Update theme description TextView
          binding.themeDescription.text = getString(AppTheme.THEME_ARRAY[checkedItem].modeNameRes)
        }
        negativeButton(getString(R.string.cancel))
      }.show()
    }
  }
}
