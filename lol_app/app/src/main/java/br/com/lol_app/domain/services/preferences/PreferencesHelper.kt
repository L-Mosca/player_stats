package br.com.lol_app.domain.services.preferences

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import br.com.lol_app.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferencesHelper @Inject constructor(@ApplicationContext val context: Context) :
    PreferencesHelperContract {


    companion object {
        private const val PREFERENCES_NAME =
            "${BuildConfig.APP_NAME}.${BuildConfig.FLAVOR}.DataStore.LolApp"

        private val userToken = stringPreferencesKey(name = "$PREFERENCES_NAME.userToken")
    }

    private val Context.dataStore by preferencesDataStore(name = PREFERENCES_NAME)
    private val dataStore = context.dataStore

}