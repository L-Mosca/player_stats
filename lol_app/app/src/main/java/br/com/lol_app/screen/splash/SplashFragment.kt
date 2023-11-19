package br.com.lol_app.screen.splash

import android.view.LayoutInflater
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.lol_app.R
import br.com.lol_app.base.BaseFragment
import br.com.lol_app.databinding.FragmentSplashBinding
import br.com.lol_app.screen.host.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentSplashBinding =
        FragmentSplashBinding::inflate
    override val viewModel: SplashViewModel by viewModels()
    private val mainSharedViewModel: MainViewModel by activityViewModels()

    override fun initViews() {
        findNavController().popBackStack(R.id.splashFragment, true)
        viewModel.loadingSplashData()
    }

    override fun initObservers() {
        viewModel.showHomeScreen.observe(viewLifecycleOwner) {
            mainSharedViewModel.showHomeScreen()
        }
    }
}