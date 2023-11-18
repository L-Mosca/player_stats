package br.com.lol_app.screen.home

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import br.com.lol_app.base.BaseFragment
import br.com.lol_app.databinding.FragmentHomeBinding
import br.com.lol_app.utils.LoadingType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate
    override val viewModel: HomeViewModel by viewModels()

    override fun initViews() {
        viewModel.fetchFreeChampionsRotation()
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
}