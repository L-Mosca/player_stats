package br.com.lol_app.screen.stats

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import br.com.lol_app.base.BaseFragment
import br.com.lol_app.databinding.FragmentFindSummonerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FindSummonerFragment : BaseFragment<FragmentFindSummonerBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentFindSummonerBinding =
        FragmentFindSummonerBinding::inflate
    override val viewModel: FindSummonerViewModel by viewModels()

    override fun initViews() {

    }

    override fun initObservers() {

    }
}