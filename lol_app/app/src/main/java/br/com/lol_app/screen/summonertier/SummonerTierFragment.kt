package br.com.lol_app.screen.summonertier

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import br.com.lol_app.R
import br.com.lol_app.base.BaseFragment
import br.com.lol_app.databinding.FragmentSummonerTierBinding
import br.com.lol_app.domain.model.tier.SummonerMainTierResponse
import br.com.lol_app.utils.getTierByName
import br.com.lol_app.utils.getTierImageByName
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class SummonerTierFragment : BaseFragment<FragmentSummonerTierBinding>() {

    companion object {
        private const val SUMMONER_ID_EXTRA = "TierSoloDuoFragment.soloDuoData"

        fun newInstance(soloDuoData: SummonerMainTierResponse?) = SummonerTierFragment().apply {
            arguments = Bundle().apply {
                putParcelable(SUMMONER_ID_EXTRA, soloDuoData)
            }
        }
    }

    override val bindingInflater: (LayoutInflater) -> FragmentSummonerTierBinding =
        FragmentSummonerTierBinding::inflate
    override val viewModel: SummonerTierViewModel by viewModels()
    private var tierData: SummonerMainTierResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tierData = arguments?.getParcelable(SUMMONER_ID_EXTRA)
        viewModel.setupInitialData(tierData)
    }

    override fun initViews() {}

    override fun initObservers() {
        viewModel.summonerTier.observe(viewLifecycleOwner) { summonerMainTierResponse ->
            setupInitialData(summonerMainTierResponse)
        }

        viewModel.rankData.observe(viewLifecycleOwner) { rankData ->
            setupRankData(rankData.first, rankData.second, rankData.third)
        }

        viewModel.winRate.observe(viewLifecycleOwner) { winRateData ->
            setupWinRateData(winRateData.first, winRateData.second, winRateData.third)
        }

        viewModel.noTierData.observe(viewLifecycleOwner) {
            binding.includeNoTierFound.clNoTierFound.isVisible = true
            binding.ivTier.isVisible = false
        }
    }

    private fun setupInitialData(summonerMainTierResponse: SummonerMainTierResponse) {
        with(summonerMainTierResponse) {
            binding.apply {
                includeNoTierFound.clNoTierFound.isVisible = false
                ivTier.setImageResource(tier.getTierImageByName())
            }
        }
    }

    private fun setupRankData(tier: String, rank: String, points: Int) {
        val tierName = getString(tier.getTierByName())
        binding.tvTier.apply {
            text = Html.fromHtml(
                getString(R.string.tier_description, tierName, rank, points),
                Html.FROM_HTML_MODE_LEGACY
            )
            isVisible = true
        }
    }

    private fun setupWinRateData(wins: Int, loses: Int, winRate: String) {
        binding.includeWinsData.apply {
            cvWinRate.isVisible = true
            tvWinRateLoses.text = loses.toString()
            tvWinRateVictories.text = wins.toString()
            val winRateMessage = "${getString(R.string.win_rate, winRate)}%"
            tvWinRateTitle.text = winRateMessage
            lpiWinRate.max = wins + loses
            lpiWinRate.progress = wins
        }
    }
}