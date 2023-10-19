package br.com.lol_app.screen.settings

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import br.com.lol_app.base.BaseFragment
import br.com.lol_app.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentSettingsBinding =
        FragmentSettingsBinding::inflate
    override val viewModel: SettingsViewModel by viewModels()

    override fun initViews() {

    }

    override fun initObservers() {

    }
}