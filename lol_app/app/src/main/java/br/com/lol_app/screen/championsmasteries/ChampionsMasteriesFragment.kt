package br.com.lol_app.screen.championsmasteries

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import br.com.lol_app.R
import br.com.lol_app.base.BaseFragment
import br.com.lol_app.databinding.FragmentChampionsMasteriesBinding
import br.com.lol_app.domain.model.summoner.SummonerResponse
import br.com.lol_app.screen.championsmasteries.adapter.MasteriesChampionsAdapter
import dagger.hilt.android.AndroidEntryPoint


@Suppress("DEPRECATION")
@AndroidEntryPoint
class ChampionsMasteriesFragment : BaseFragment<FragmentChampionsMasteriesBinding>() {

    companion object {
        private const val SUMMONER_DATA_EXTRA = "SummonerData.EXTRA"

        fun newInstance(summonerData: SummonerResponse) = ChampionsMasteriesFragment().apply {
            arguments = Bundle().apply {
                putParcelable(SUMMONER_DATA_EXTRA, summonerData)
            }
        }
    }

    override val bindingInflater: (LayoutInflater) -> FragmentChampionsMasteriesBinding =
        FragmentChampionsMasteriesBinding::inflate
    override val viewModel: ChampionsMasteriesViewModel by viewModels()
    private val adapter = MasteriesChampionsAdapter()
    private var summonerData: SummonerResponse? = null

    override fun initViews() {
        binding.ivToggleViewChampionsMasteries.setOnClickListener {
            viewModel.setListAnimation(binding.rvChampionsMasteries.isVisible, requireContext())
        }
    }

    override fun initObservers() {
        viewModel.slideUpAnimation.observe(viewLifecycleOwner) { animation ->
            with(binding) {
                ivToggleViewChampionsMasteries.startAnimation(animation.second)
                rvChampionsMasteries.startAnimation(animation.first)
                ivToggleViewChampionsMasteries.setImageResource(R.drawable.ic_up)
                rvChampionsMasteries.visibility = View.INVISIBLE
                Handler(Looper.getMainLooper()).postDelayed({
                    rvChampionsMasteries.visibility = View.GONE
                }, 250)

            }
        }

        viewModel.slideDownAnimation.observe(viewLifecycleOwner) { animation ->
            with(binding) {
                ivToggleViewChampionsMasteries.startAnimation(animation.second)
                rvChampionsMasteries.startAnimation(animation.first)
                ivToggleViewChampionsMasteries.setImageResource(R.drawable.ic_down)
                rvChampionsMasteries.visibility = View.VISIBLE
            }
        }

        viewModel.championsMasteries.observe(viewLifecycleOwner) { championList ->
            binding.apply {
                adapter.dataList = championList!!.subList(0, 5)
                rvChampionsMasteries.adapter = adapter
            }

//            adapter.onChampionClicked = { _ ->
//                val directions =
//                    SummonerDetailFragmentDirections.actionSummonerDetailFragmentToChampionMasteryDetailFragment(
//                        summonerData = summonerData!!
//                    )
//                navigate(directions)
//            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        summonerData = arguments?.getParcelable(SUMMONER_DATA_EXTRA)
        viewModel.fetchChampionsMasteries(summonerData?.id!!)
    }
}