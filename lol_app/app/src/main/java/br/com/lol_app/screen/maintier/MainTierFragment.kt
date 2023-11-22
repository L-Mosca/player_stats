package br.com.lol_app.screen.maintier

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import br.com.lol_app.base.BaseFragment
import br.com.lol_app.databinding.FragmentSummonerMainTierBinding
import br.com.lol_app.domain.model.tier.SummonerMainTierResponse
import br.com.lol_app.screen.maintier.MainTierFragment.Companion.newInstance
import dagger.hilt.android.AndroidEntryPoint


/**
 * This fragment show summoner main tier data and are used in SummonerDetailFragment in _setupTierData_ (create an instance of this class with newInstance function)
 * @see newInstance
 * @see SummonerMainTierResponse
 */
@AndroidEntryPoint
class MainTierFragment : BaseFragment<FragmentSummonerMainTierBinding>() {

    /**
     * @property MAIN_TIER_EXTRA this constant represent a Bundle key to put and get summonerId (string data)
     */
    companion object {
        private const val MAIN_TIER_EXTRA = "MainTierData.EXTRA"

        /**
         * This function create a new instance of *__MainTierFragment__* and putString _summonerId_ at fragment bundle
         * @param summonerId This string are used to fetch summoner tier data
         * @return Return an instance of MainTierFragment and put string at Bundle
         * @see MainTierFragment
         */
        fun newInstance(summonerId: String) =
            MainTierFragment().apply {
                arguments = Bundle().apply {
                    putString(MAIN_TIER_EXTRA, summonerId)
                }
            }
    }

    override val bindingInflater: (LayoutInflater) -> FragmentSummonerMainTierBinding =
        FragmentSummonerMainTierBinding::inflate
    override val viewModel: MainTierViewModel by viewModels()
    private var summonerId: String? = null

    override fun initViews() {

    }

    override fun initObservers() {
        viewModel.mainTierData.observe(viewLifecycleOwner) { summonerMainTierResponse ->

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        summonerId = arguments?.getString(MAIN_TIER_EXTRA)
        viewModel.fetchMainTierData(summonerId.toString())
    }

}