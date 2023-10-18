package br.com.lol_app.screen.stats

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import br.com.lol_app.base.BaseFragment
import br.com.lol_app.databinding.FragmentStatsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatsFragment : BaseFragment<FragmentStatsBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentStatsBinding =
        FragmentStatsBinding::inflate
    override val viewModel: StatsViewModel by viewModels()

    override fun initViews() {

    }

    override fun initObservers() {

    }
}