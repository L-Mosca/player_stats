package br.com.lol_app.di

import br.com.lol_app.BuildConfig
import br.com.lol_app.domain.services.api.ApiHelper
import br.com.lol_app.domain.services.api.ApiHelperContract
import br.com.lol_app.domain.services.api.DbApi
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
    fun provideDbApi(preferencesHelper: PreferencesHelperContract) : DbApi = DbApi.newInstance(preferencesHelper, BuildConfig.API_URL)

    @Provides
    @Singleton
    fun provideApi(dbApi: DbApi): ApiHelperContract = ApiHelper(dbApi)
}