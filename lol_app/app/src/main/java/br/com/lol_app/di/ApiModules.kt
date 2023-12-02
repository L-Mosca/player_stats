package br.com.lol_app.di

import br.com.lol_app.BuildConfig
import br.com.lol_app.domain.services.api.ApiHelper
import br.com.lol_app.domain.services.api.ApiHelperContract
import br.com.lol_app.domain.services.api.DbApi
import br.com.lol_app.domain.services.api.matchclient.MatchApi
import br.com.lol_app.domain.services.preferences.PreferencesHelperContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModules {

    @Provides
    @Singleton
    fun provideDbApi(preferencesHelper: PreferencesHelperContract): DbApi =
        DbApi.newInstance(preferencesHelper, BuildConfig.API_SUMMONER_URL)

    @Provides
    @Singleton
    fun provideMatchApi(preferencesHelper: PreferencesHelperContract): MatchApi =
        MatchApi.newInstance(preferencesHelper, BuildConfig.API_MATCH_URL)

    @Provides
    @Singleton
    fun provideApi(dbApi: DbApi, matchApi: MatchApi): ApiHelperContract = ApiHelper(dbApi, matchApi)
}