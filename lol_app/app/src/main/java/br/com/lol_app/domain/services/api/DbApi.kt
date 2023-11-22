package br.com.lol_app.domain.services.api

import br.com.lol_app.BuildConfig
import br.com.lol_app.domain.model.champions.ChampionBaseData
import br.com.lol_app.domain.model.champions.FreeChampionsResponse
import br.com.lol_app.domain.model.summoner.SummonerResponse
import br.com.lol_app.domain.model.tier.SummonerMainTierResponse
import br.com.lol_app.domain.services.preferences.PreferencesHelperContract
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface DbApi {
    companion object {
        fun newInstance(preferencesHelper: PreferencesHelperContract, url: String): DbApi {
            val okHttpClient = getOkHttpClient(preferencesHelper)
            val retrofit = getRetrofit(okHttpClient, url)
            return retrofit.create(DbApi::class.java)
        }

        private fun getRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
            return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .baseUrl(url)
                .build()
        }

        private fun getOkHttpClient(preferencesHelper: PreferencesHelperContract): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE

            return OkHttpClient.Builder()
                .readTimeout(1000, TimeUnit.SECONDS)
                .addNetworkInterceptor(StethoInterceptor())
                .addInterceptor(interceptor)
                .addInterceptor { chain ->
                    var newRequest = chain.request()


                    newRequest =
                        newRequest.newBuilder()
                            .addHeader("X-Riot-Token", BuildConfig.API_KEY)
                            .build()
                    /*preferencesHelper.getToken()?.let { token ->
                        newRequest = newRequest.newBuilder()
                            .addHeader("Authorization", "Bearer $token")
                            .build()
                    }*/

                    chain.proceed(newRequest)
                }
                .build()
        }
    }

    @GET(ApiConstants.SUMMONER_BY_NAME)
    suspend fun fetchSummonerByName(@Path("name") name: String): SummonerResponse?

    @GET(ApiConstants.FREE_CHAMPIONS_ROTATION)
    suspend fun fetchFreeChampionsRotation(): FreeChampionsResponse?

    @GET(ApiConstants.CHAMPIONS_MASTERIES_BY_SUMMONER_ID)
    suspend fun fetchChampionsMasteries(@Path("encryptedSummonerId") summonerId: String): List<ChampionBaseData>?

    /**
     * Fetch summoner main tier basic data
     * @param summonerId use how path at *__ApiConstants.SUMMONER_MAIN_TIER__* string to get data in external API.
     * @return Return SummonerMainTierResponse class with summoner main tier basic data - __/domain/model/tier/SummonerMainTierResponse__
     * @see SummonerMainTierResponse
     * @see ApiConstants.SUMMONER_MAIN_TIER
     */
    @GET(ApiConstants.SUMMONER_MAIN_TIER)
    suspend fun fetchSummonerMainTier(@Path("encryptedSummonerId") summonerId: String): SummonerMainTierResponse?
}