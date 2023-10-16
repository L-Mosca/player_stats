package br.com.lol_app.screen.home

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import br.com.lol_app.base.BaseFragment
import br.com.lol_app.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate
    override val viewModel: HomeViewModel by viewModels()

    override fun initViews() {
        viewModel.test()
    }

    override fun initObservers() {
        viewModel.test.observe(viewLifecycleOwner) {
            showLongToast(it)
        }
    }
}