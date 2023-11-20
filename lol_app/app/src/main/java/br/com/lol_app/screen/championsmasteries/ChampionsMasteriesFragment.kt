package br.com.lol_app.screen.championsmasteries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.lol_app.databinding.FragmentChampionsMasteriesBinding
import br.com.lol_app.domain.model.summoner.SummonerResponse
import br.com.lol_app.screen.summonerdetail.adapter.MasteriesChampionsAdapter
import br.com.lol_app.utils.getChampionNameById
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class ChampionsMasteriesFragment : Fragment() {

    companion object {
        private const val SUMMONER_DATA_EXTRA = "SummonerData.EXTRA"

        fun newInstance(summonerData: SummonerResponse) = ChampionsMasteriesFragment().apply {
            arguments = Bundle().apply {
                putParcelable(SUMMONER_DATA_EXTRA, summonerData)
            }
        }
    }

    lateinit var binding: FragmentChampionsMasteriesBinding
    private val viewModel: ChampionsMasteriesViewModel by viewModels()
    private val adapter = MasteriesChampionsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChampionsMasteriesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() {
        val data = arguments?.getParcelable<SummonerResponse>(SUMMONER_DATA_EXTRA)
        viewModel.fetchChampionsMasteries(data?.id!!)
    }

    private fun initObservers() {
        viewModel.championsMasteries.observe(viewLifecycleOwner) { championList ->
            binding.apply {
                adapter.dataList = championList!!.subList(0, 5)
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
}