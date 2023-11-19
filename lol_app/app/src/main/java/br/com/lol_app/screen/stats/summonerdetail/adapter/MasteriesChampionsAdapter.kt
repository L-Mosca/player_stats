package br.com.lol_app.screen.stats.summonerdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import br.com.lol_app.base.BaseAdapter
import br.com.lol_app.base.ViewHolder
import br.com.lol_app.databinding.AdapterChampionsMasteriesBinding
import br.com.lol_app.domain.model.champions.ChampionBaseData
import br.com.lol_app.utils.getChampionNameById
import br.com.lol_app.utils.toDpMetric

class MasteriesChampionsAdapter :
    BaseAdapter<AdapterChampionsMasteriesBinding, ChampionBaseData>() {

    companion object {
        const val FIRST_VIEW = 0
        const val DEFAULT_VIEW = 1
        const val LAST_VIEW = 2
    }

    override val bindingInflater: (LayoutInflater, ViewGroup) -> AdapterChampionsMasteriesBinding
        get() = { layoutInflater, viewGroup ->
            AdapterChampionsMasteriesBinding.inflate(
                layoutInflater,
                viewGroup,
                false
            )
        }

    var onChampionClicked: ((ChampionBaseData) -> Unit)? = null

    override fun onBindViewHolder(
        holder: ViewHolder<AdapterChampionsMasteriesBinding>,
        data: ChampionBaseData,
        position: Int
    ) {
        holder.binding.apply {
            cvChampionMastery.setOnClickListener { onChampionClicked?.invoke(data) }
            tvChampionName.text = data.championId.getChampionNameById()
            tvChampionDescription.text = data.championId.getChampionNameById()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<AdapterChampionsMasteriesBinding> {
        val binding = AdapterChampionsMasteriesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        // custom first item margin
        if (viewType == FIRST_VIEW) {
            binding.root.updateLayoutParams<RecyclerView.LayoutParams> {
                marginStart = 20.toDpMetric(parent)
            }
        }

        // Custom last item margin
        if (viewType == LAST_VIEW) {
            binding.root.updateLayoutParams<RecyclerView.LayoutParams> {
                marginEnd = 20.toDpMetric(parent)
            }
        }

        return ViewHolder(binding)
    }

    // Override getItemViewType to identify first and last view item
    override fun getItemViewType(position: Int) = when (position) {
        0 -> FIRST_VIEW
        dataList.lastIndex -> LAST_VIEW
        else -> DEFAULT_VIEW
    }

}