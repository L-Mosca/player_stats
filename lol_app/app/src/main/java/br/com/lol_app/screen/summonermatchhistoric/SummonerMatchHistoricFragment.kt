package br.com.lol_app.screen.summonermatchhistoric

import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import br.com.lol_app.R
import br.com.lol_app.base.BaseFragment
import br.com.lol_app.databinding.FragmentSummonerMatchHistoricBinding
import br.com.lol_app.utils.LoadingType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SummonerMatchHistoricFragment : BaseFragment<FragmentSummonerMatchHistoricBinding>() {

    companion object {
        private const val SUMMONER_DATA_EXTRA = "SummonerMatchHistoric.EXTRA"

        fun newInstance(summonerPUUId: String) = SummonerMatchHistoricFragment().apply {
            arguments = Bundle().apply {
                putString(SUMMONER_DATA_EXTRA, summonerPUUId)
            }
        }
    }


    override val bindingInflater: (LayoutInflater) -> FragmentSummonerMatchHistoricBinding =
        FragmentSummonerMatchHistoricBinding::inflate
    override val viewModel: SummonerMatchHistoricViewModel by viewModels()

    override fun initViews() {

    }

    override fun initObservers() {
        viewModel.test.observe(viewLifecycleOwner) {
            binding.tvTest.text = it
        }

        viewModel.noHistoricFound.observe(viewLifecycleOwner) {
            binding.includeNoTierFound.apply {
                tvNoTierData.text = getString(R.string.no_match_found)
                clNoTierFound.isVisible = true
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            showLoading(
                binding.includeLoading,
                isLoading = isLoading,
                loadingType = LoadingType.SECONDARY_LOADING
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchLastMatchesHistoric(arguments?.getString(SUMMONER_DATA_EXTRA)!!)
    }
}