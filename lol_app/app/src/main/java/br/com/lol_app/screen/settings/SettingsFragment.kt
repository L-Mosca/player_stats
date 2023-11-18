package br.com.lol_app.screen.settings

import android.view.LayoutInflater
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.lol_app.R
import br.com.lol_app.base.BaseFragment
import br.com.lol_app.databinding.FragmentSettingsBinding
import br.com.lol_app.screen.host.MainViewModel
import br.com.lol_app.utils.onBackPressed
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentSettingsBinding =
        FragmentSettingsBinding::inflate
    override val viewModel: SettingsViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun initViews() {
        setupBackPressed()
    }

    override fun initObservers() {

    }

    private fun setupBackPressed() {
        onBackPressed {
            findNavController().popBackStack(R.id.home_nav_graph, false)
            mainViewModel.showHomeScreen()
        }
    }
}