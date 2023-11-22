package br.com.lol_app.screen.allchampionmastery

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import br.com.lol_app.base.BaseFragment
import br.com.lol_app.databinding.FragmentAllChampionMasteryBinding
import br.com.lol_app.screen.championsmasteries.adapter.MasteriesChampionsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllChampionMasteryFragment : BaseFragment<FragmentAllChampionMasteryBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentAllChampionMasteryBinding =
        FragmentAllChampionMasteryBinding::inflate
    override val viewModel: AllChampionMasteryViewModel by viewModels()
    private val args: AllChampionMasteryFragmentArgs by navArgs()
    private val adapter = MasteriesChampionsAdapter()

    override fun initViews() {
        viewModel.fetchChampionsMasteries(args.summonerData.id.toString())
    }

    override fun initObservers() {
        viewModel.championsMasteries.observe(viewLifecycleOwner) { championList ->
            binding.apply {
                adapter.dataList = championList!!.subList(0, 5)
                //rvChampionsMasteries.adapter = adapter
            }

            adapter.onChampionClicked = { _ -> }
        }
    }
}