package br.com.lol_app.screen.home

import android.view.LayoutInflater
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import br.com.lol_app.base.BaseFragment
import br.com.lol_app.databinding.FragmentHomeBinding
import br.com.lol_app.screen.host.MainViewModel
import br.com.lol_app.utils.LoadingType
import br.com.lol_app.utils.onBackPressed
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate
    override val viewModel: HomeViewModel by viewModels()
    private val mainSharedViewModel: MainViewModel by activityViewModels()

    override fun initViews() {
        setupBackPressed()
        mainSharedViewModel.showNavBottom(true)
        viewModel.fetchFreeChampionsRotation(requireContext())
    }

    override fun initObservers() {
        viewModel.freeChampionList.observe(viewLifecycleOwner) { championList ->
            var names = ""
            championList.forEach {
                names += "$it\n"
            }
            binding.tvFreeChampions.text = names
        }

        viewModel.freeChampionsForNewPlayers.observe(viewLifecycleOwner) { championList ->
            var names = ""
            championList.forEach {
                names += "$it\n"
            }
            binding.tvFreeChampionsForNewPlayers.text = names
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            showLoading(
                binding.includeLoading,
                isLoading = isLoading,
                loadingType = LoadingType.SECONDARY_LOADING
            )
        }
    }

    private fun setupBackPressed() {
        onBackPressed {
            requireActivity().moveTaskToBack(true)
        }
    }
}