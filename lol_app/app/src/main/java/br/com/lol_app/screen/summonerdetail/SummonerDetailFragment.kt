package br.com.lol_app.screen.summonerdetail

import android.view.LayoutInflater
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import br.com.lol_app.R
import br.com.lol_app.base.BaseFragment
import br.com.lol_app.databinding.FragmentSummonerDetailBinding
import br.com.lol_app.screen.championsmasteries.ChampionsMasteriesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SummonerDetailFragment : BaseFragment<FragmentSummonerDetailBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentSummonerDetailBinding =
        FragmentSummonerDetailBinding::inflate
    override val viewModel: SummonerDetailViewModel by viewModels()
    private val navArgs: SummonerDetailFragmentArgs by navArgs()

    override fun initViews() {
        viewModel.fetchSummonerStats(navArgs.summonerData)
        setupChampionsMasteries()
    }

    override fun initObservers() {
        viewModel.summonerBaseData.observe(viewLifecycleOwner) { summonerBaseData ->
            with(summonerBaseData) {
                binding.includeSummonerBaseData.apply {
                    tvName.text = name
                    tvLevel.text = requireContext().getString(R.string.summoner_level, level)
                }
            }
        }
    }

    private fun setupChampionsMasteries() {
        val fragment = ChampionsMasteriesFragment.newInstance(navArgs.summonerData)

        childFragmentManager.commit {
            replace(R.id.fcvChampionsMasteries, fragment)
        }

    }
}