package br.com.lol_app.screen.findsummoner.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.lol_app.base.BaseAdapter
import br.com.lol_app.base.ViewHolder
import br.com.lol_app.databinding.AdapterRecentSummonerBinding
import br.com.lol_app.domain.model.summoner.SummonerResponse

class RecentSummonerAdapter : BaseAdapter<AdapterRecentSummonerBinding, SummonerResponse>() {
    override val bindingInflater: (LayoutInflater, ViewGroup) -> AdapterRecentSummonerBinding
        get() = { layoutInflater, viewGroup ->
            AdapterRecentSummonerBinding.inflate(
                layoutInflater,
                viewGroup,
                false
            )
        }

    var onItemClicked: ((SummonerResponse) -> Unit)? = null
    var onDeleteClicked: ((SummonerResponse, Int) -> Unit)? = null
    var onFavoriteClicked: ((SummonerResponse, Int) -> Unit)? = null

    override fun onBindViewHolder(
        holder: ViewHolder<AdapterRecentSummonerBinding>,
        data: SummonerResponse,
        position: Int
    ) {
        with(holder.binding) {
            clRecentSummoner.setOnClickListener { onItemClicked?.invoke(data) }
            ivDelete.setOnClickListener { onDeleteClicked?.invoke(data, position) }
            ivFavorite.setOnClickListener { onFavoriteClicked?.invoke(data, position) }

            tvSummonerName.text = data.name
            tvSummonerRank.text = "Unranked"
        }
    }
}