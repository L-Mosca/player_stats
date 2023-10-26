package br.com.lol_app.screen.stats.home

import android.view.LayoutInflater
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import br.com.lol_app.base.BaseFragment
import br.com.lol_app.databinding.FragmentHomeStatsBinding
import br.com.lol_app.screen.host.MainViewModel
import br.com.lol_app.utils.TransitionAnimation
import br.com.lol_app.utils.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeStatsFragment : BaseFragment<FragmentHomeStatsBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentHomeStatsBinding =
        FragmentHomeStatsBinding::inflate
    override val viewModel: HomeStatsViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun initViews() {
        binding.vSummonerField.setOnClickListener {
            val direction =
                HomeStatsFragmentDirections.actionHomeStatsFragmentToFindSummonerFragment()
            mainViewModel.showNavBottom(false)
            navigate(direction, animation = TransitionAnimation.NO_ANIMATION)
        }
    }

    override fun initObservers() {

    }

    override fun onResume() {
        super.onResume()
        mainViewModel.showNavBottom(true)
    }
}