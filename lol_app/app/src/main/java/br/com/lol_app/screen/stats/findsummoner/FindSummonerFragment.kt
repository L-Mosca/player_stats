package br.com.lol_app.screen.stats.findsummoner

import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.lol_app.R
import br.com.lol_app.base.BaseFragment
import br.com.lol_app.databinding.FragmentFindSummonerBinding
import br.com.lol_app.domain.model.summoner.SummonerResponse
import br.com.lol_app.screen.host.MainViewModel
import br.com.lol_app.screen.stats.findsummoner.adapter.RecentSummonerAdapter
import br.com.lol_app.utils.LoadingType
import br.com.lol_app.utils.hideKeyboard
import br.com.lol_app.utils.navigate
import br.com.lol_app.utils.onBackPressed
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FindSummonerFragment : BaseFragment<FragmentFindSummonerBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentFindSummonerBinding =
        FragmentFindSummonerBinding::inflate
    override val viewModel: FindSummonerViewModel by viewModels()
    private val mainSharedViewModel: MainViewModel by activityViewModels()
    private val adapter = RecentSummonerAdapter()

    override fun initViews() {
        setupBackPressed()
        setupEditText()
        setupRecycler()
        setupRecentSummonerAnimation()
    }

    override fun initObservers() {
        viewModel.slideUpAnimation.observe(viewLifecycleOwner) { animation ->
            with(binding.includeRecentSummoner) {
                ivToggleView.startAnimation(animation.second)
                rvRecentSearch.startAnimation(animation.first)
                ivToggleView.setImageResource(R.drawable.ic_up)
                rvRecentSearch.visibility = View.INVISIBLE
            }
        }

        viewModel.slideDownAnimation.observe(viewLifecycleOwner) { animation ->
            with(binding.includeRecentSummoner) {
                ivToggleView.startAnimation(animation.second)
                rvRecentSearch.startAnimation(animation.first)
                ivToggleView.setImageResource(R.drawable.ic_down)
                rvRecentSearch.visibility = View.VISIBLE
            }
        }

        viewModel.summonerResponse.observe(viewLifecycleOwner) { summonerResponse ->
            if (summonerResponse != null) {
                val direction =
                    FindSummonerFragmentDirections.actionStatsFragmentToSummonerDetailFragment(
                        summonerData = summonerResponse
                    )
                navigate(direction)
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            showLoading(
                binding.includeLoading,
                isLoading = isLoading,
                loadingType = LoadingType.SECONDARY_LOADING
            )
        }

        viewModel.summonerNotFound.observe(viewLifecycleOwner) { showPlaceHolder ->
            showEmptyPlaceHolder(showPlaceHolder)
        }

        viewModel.setFocus.observe(viewLifecycleOwner) {
            binding.tilSummonerName.clearFocus()
            binding.etSummonerName.clearFocus()
            hideKeyboard()
        }
    }

    private fun setupEditText() {
        binding.etSummonerName.setOnEditorActionListener { textView, actionId, _ ->
            viewModel.fetchSummonerByName(textView.text.toString(), actionId)
            viewModel.setFocus(actionId)
            showEmptyPlaceHolder(false)
            false
        }
    }

    private fun showEmptyPlaceHolder(showPlaceHolder: Boolean) {
        if (showPlaceHolder)
            binding.includeEmptySummoner.llEmptySummoner.visibility = View.VISIBLE
        else
            binding.includeEmptySummoner.llEmptySummoner.visibility = View.GONE
    }

    private fun setupRecycler() {
        val list = mutableListOf<SummonerResponse>()

        var i = 0
        while (i < 15) {
            list.add(
                SummonerResponse(
                    id = "1",
                    accountId = "1",
                    pUUid = "1",
                    name = "Lêmure de Muleta",
                    profileIconId = 1,
                    revisionDate = 1000,
                    summonerLevel = 400
                )
            )
            i++
        }

        adapter.dataList = list
        binding.includeRecentSummoner.rvRecentSearch.adapter = adapter

        adapter.onItemClicked = {}
        adapter.onFavoriteClicked = { _, _ -> }
        adapter.onDeleteClicked = { _, _ -> }
    }

    private fun setupRecentSummonerAnimation() {
        with(binding.includeRecentSummoner) {
            ivToggleView.setOnClickListener {
                viewModel.setListAnimation(rvRecentSearch.isVisible, requireContext())
            }
        }
    }

    private fun setupBackPressed() {
        onBackPressed {
            findNavController().popBackStack(R.id.home_nav_graph, false)
            mainSharedViewModel.showHomeScreen()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.resetSummonerValue()
    }
}