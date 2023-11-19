package br.com.lol_app.screen.stats.summonerdetail

import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import br.com.lol_app.R
import br.com.lol_app.base.BaseFragment
import br.com.lol_app.databinding.FragmentSummonerDetailBinding
import br.com.lol_app.domain.model.champions.ChampionBaseData
import br.com.lol_app.screen.stats.summonerdetail.adapter.MasteriesChampionsAdapter
import br.com.lol_app.utils.getChampionNameById
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SummonerDetailFragment : BaseFragment<FragmentSummonerDetailBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentSummonerDetailBinding =
        FragmentSummonerDetailBinding::inflate
    override val viewModel: SummonerDetailViewModel by viewModels()

    private val navArgs: SummonerDetailFragmentArgs by navArgs()
    private val adapter = MasteriesChampionsAdapter()

    override fun initViews() {
        viewModel.fetchSummonerStats(navArgs.summonerData)
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

        viewModel.championsMasteries.observe(viewLifecycleOwner) { championList ->
            setupRecycler(championList!!)
        }
    }

    private fun setupRecycler(championList: List<ChampionBaseData>) {
        binding.includeChampionsMastery.apply {
            adapter.dataList = championList.subList(0, 4)
            rvChampionsMasteries.adapter = adapter
        }

        adapter.onChampionClicked = { championData ->
            Toast.makeText(
                requireContext(),
                championData.championId.getChampionNameById(),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}