package br.com.lol_app.screen.summonerdetail

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.lol_app.R
import br.com.lol_app.base.BaseFragment
import br.com.lol_app.databinding.FragmentSummonerDetailBinding
import br.com.lol_app.screen.championsmasteries.ChampionsMasteriesFragment
import br.com.lol_app.screen.maintier.MainTierFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SummonerDetailFragment : BaseFragment<FragmentSummonerDetailBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentSummonerDetailBinding =
        FragmentSummonerDetailBinding::inflate
    override val viewModel: SummonerDetailViewModel by viewModels()
    private val navArgs: SummonerDetailFragmentArgs by navArgs()

    override fun initViews() {
        binding.includeSummonerBaseData.ivBackSummonerDetail.setOnClickListener { findNavController().popBackStack() }
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
    }

    private fun setupChampionsMasteries() {
        val fragment = ChampionsMasteriesFragment.newInstance(navArgs.summonerData)
        childFragmentManager.commit {
            replace(R.id.fcvChampionsMasteries, fragment)
        }

    }

    private fun setupTierData() {
        val fragment = MainTierFragment.newInstance(navArgs.summonerData.id.toString())
        childFragmentManager.commit {
            replace(R.id.fcvTier, fragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setupChampionsMasteries()
        setupTierData()
        super.onCreate(savedInstanceState)
    }
}