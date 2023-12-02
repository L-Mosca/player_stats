package br.com.lol_app.screen.home

import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import br.com.lol_app.R
import br.com.lol_app.base.BaseFragment
import br.com.lol_app.databinding.FragmentHomeBinding
import br.com.lol_app.screen.host.MainViewModel
import br.com.lol_app.utils.LoadingType
import br.com.lol_app.utils.onBackPressed
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate
    override val viewModel: HomeViewModel by viewModels()
    private val mainSharedViewModel: MainViewModel by activityViewModels()

    override fun initViews() {
        setupBackPressed()
        mainSharedViewModel.showNavBottom(true)
        //viewModel.fetchFreeChampionsRotation(requireContext())

        val list = ArrayList<Entry>()

        list.add(Entry(0f, 0f))
        list.add(Entry(1f, 10f))
        list.add(Entry(2f, 20f))
        list.add(Entry(3f, 32f))
        list.add(Entry(4f, 43f))
        list.add(Entry(5f, 52f))
        list.add(Entry(6f, 64f))
        list.add(Entry(7f, 42f))

        val lineDataSet = LineDataSet(list, "data set")

        val iLineDataSet = ArrayList<LineDataSet>()
        iLineDataSet.add(lineDataSet)

        lineDataSet.apply {
            color = ContextCompat.getColor(requireContext(), R.color.gold_500)
            setDrawCircleHole(true)
            setDrawCircles(true)
            circleHoleColor = ContextCompat.getColor(requireContext(), R.color.blue_050)
            lineWidth = 2f
            circleRadius = 10f
            valueTextSize = 12f
            valueTextColor = ContextCompat.getColor(requireContext(), R.color.gold_400)
            setCircleColor(ContextCompat.getColor(requireContext(), R.color.blue_050))
        }

        val lineData = LineData(iLineDataSet as List<ILineDataSet>?)

        binding.lcGraph.apply {
            data = lineData
            invalidate()

            axisRight.apply {
                setDrawGridLines(false)
                setDrawLabels(false)
                setDrawAxisLine(false)
            }
            axisLeft.apply {
                setDrawGridLines(false)
                setDrawLabels(false)
                setDrawAxisLine(false)
            }
            xAxis.apply {
                
                setDrawGridLines(false)
                setDrawLabels(false)
                setDrawAxisLine(false)
            }
            setDrawGridBackground(false)
            setDrawBorders(false)
        }
    }

    override fun initObservers() {
        viewModel.freeChampionList.observe(viewLifecycleOwner) { championList ->
            /*var names = ""
            championList.forEach {
                names += "$it\n"
            }
            binding.tvFreeChampions.text = names*/
        }

        viewModel.freeChampionsForNewPlayers.observe(viewLifecycleOwner) { championList ->
            /*var names = ""
            championList.forEach {
                names += "$it\n"
            }
            binding.tvFreeChampionsForNewPlayers.text = names*/
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            showLoading(
                binding.includeLoading,
                isLoading = isLoading,
                loadingType = LoadingType.SECONDARY_LOADING
            )
        }
    }

    private fun setupBackPressed() {
        onBackPressed {
            requireActivity().moveTaskToBack(true)
        }
    }
}