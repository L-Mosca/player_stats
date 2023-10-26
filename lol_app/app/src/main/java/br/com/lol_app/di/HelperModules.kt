package br.com.lol_app.di

import br.com.lol_app.domain.services.preferences.PreferencesHelper
import br.com.lol_app.domain.services.preferences.PreferencesHelperContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface HelperModules {

    @Singleton
    @Binds
    fun bindPreferencesHelper(preferencesHelper: PreferencesHelper): PreferencesHelperContract
}