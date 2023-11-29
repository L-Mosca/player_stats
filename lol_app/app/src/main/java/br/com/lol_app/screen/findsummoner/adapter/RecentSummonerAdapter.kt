package br.com.lol_app.screen.findsummoner.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.lol_app.R
import br.com.lol_app.base.BaseAdapter
import br.com.lol_app.base.ViewHolder
import br.com.lol_app.databinding.AdapterRecentSummonerBinding
import br.com.lol_app.domain.model.summoner.SummonerEntity
import br.com.lol_app.utils.getTierByName
import br.com.lol_app.utils.getTierImageByName

class RecentSummonerAdapter : BaseAdapter<AdapterRecentSummonerBinding, SummonerEntity>() {
    override val bindingInflater: (LayoutInflater, ViewGroup) -> AdapterRecentSummonerBinding
        get() = { layoutInflater, viewGroup ->
            AdapterRecentSummonerBinding.inflate(
                layoutInflater,
                viewGroup,
                false
            )
        }

    var onItemClicked: ((SummonerEntity) -> Unit)? = null
    var onDeleteClicked: ((SummonerEntity, Int) -> Unit)? = null
    var onFavoriteClicked: ((SummonerEntity, Int) -> Unit)? = null

    override fun onBindViewHolder(
        holder: ViewHolder<AdapterRecentSummonerBinding>,
        data: SummonerEntity,
        position: Int
    ) {
        holder.binding.apply {
            clRecentSummoner.setOnClickListener { onItemClicked?.invoke(data) }
            ivDelete.setOnClickListener { onDeleteClicked?.invoke(data, position) }
            ivFavorite.setOnClickListener { onFavoriteClicked?.invoke(data, position) }
            tvSummonerName.text = data.name
            val tier = root.context.getString(data.tier.getTierByName())
            tvSummonerRank.text = Html.fromHtml(
                root.context.getString(
                    R.string.tier_description,
                    tier,
                    data.rank,
                    data.leaguePoints
                ),
                Html.FROM_HTML_MODE_LEGACY
            )
            ivSummonerRank.setBackgroundResource(data.tier.getTierImageByName())
            ivSummonerIcon.setBackgroundResource(data.tier.getTierImageByName())
        }
    }
}