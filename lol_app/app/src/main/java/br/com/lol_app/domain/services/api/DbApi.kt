package br.com.lol_app.domain.services.api

import br.com.lol_app.BuildConfig
import br.com.lol_app.domain.model.champions.ChampionBaseData
import br.com.lol_app.domain.model.champions.FreeChampionsResponse
import br.com.lol_app.domain.model.matches.MatchDetailResponse
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
import retrofit2.http.Path
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

    /**
     * Fetch summoner data by name
     * @param name Summoner name insert by user
     * @return Return an instance of SummonerResponse
     * @see SummonerResponse
     */
    @GET(ApiConstants.SUMMONER_BY_NAME)
    suspend fun fetchSummonerByName(@Path("name") name: String): SummonerResponse?

    /**
     * Fetch free champions rotation (free week)
     * @return Return an instance of FreeChampionsResponse
     * @see FreeChampionsResponse
     */
    @GET(ApiConstants.FREE_CHAMPIONS_ROTATION)
    suspend fun fetchFreeChampionsRotation(): FreeChampionsResponse?

    /**
     * Fetch summoner champions masteries (by descending order)
     * @param summonerId Summoner ID to fetch (SummonerResponse.ID)
     * @return Return a List of ChampionBaseData
     * @see SummonerResponse.id
     * @see ChampionBaseData
     */
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
    suspend fun fetchSummonerMainTier(@Path("encryptedSummonerId") summonerId: String): List<SummonerMainTierResponse?>

    /**
     * Fetch last 100 games historic ID
     * @param pUUid Player Universally Unique Identifier
     * @return Return a list of 100 last games IDs in string format
     */
    @GET(ApiConstants.FETCH_MATCHES_HISTORIC_ID)
    suspend fun fetchHistoricList(@Path("puuid") pUUid: String): List<String>

    /**
     * Fetch match historic data
     * @param matchId Match ID
     * @return Return an instance of MatchDetailResponse with full match data
     * @see MatchDetailResponse
     */
    @GET(ApiConstants.FETCH_MATCH_HISTORIC_DATA)
    suspend fun fetchMatchHistoricData(@Path("matchId") matchId: String): MatchDetailResponse
}