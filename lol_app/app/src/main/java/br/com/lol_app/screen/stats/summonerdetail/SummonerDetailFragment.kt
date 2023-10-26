package br.com.lol_app.screen.stats.summonerdetail

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import br.com.lol_app.base.BaseFragment
import br.com.lol_app.base.BaseViewModel
import br.com.lol_app.databinding.FragmentSummonerDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SummonerDetailFragment : BaseFragment<FragmentSummonerDetailBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentSummonerDetailBinding =
        FragmentSummonerDetailBinding::inflate
    override val viewModel: SummonerDetailViewModel by viewModels()

    override fun initViews() {

    }

    override fun initObservers() {

    }
}