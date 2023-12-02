package br.com.lol_app.di

import br.com.lol_app.domain.repositories.champion.ChampionRepository
import br.com.lol_app.domain.repositories.champion.ChampionRepositoryContract
import br.com.lol_app.domain.repositories.match.MatchRepository
import br.com.lol_app.domain.repositories.match.MatchRepositoryContract
import br.com.lol_app.domain.repositories.user.SummonerRepository
import br.com.lol_app.domain.repositories.user.SummonerRepositoryContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModules {

    @Singleton
    @Binds
    fun bindSummonerRepository(summonerRepository: SummonerRepository): SummonerRepositoryContract

    @Singleton
    @Binds
    fun bindChampionRepository(championRepository: ChampionRepository): ChampionRepositoryContract

    @Singleton
    @Binds
    fun bindMatchRepository(matchRepository: MatchRepository): MatchRepositoryContract
}