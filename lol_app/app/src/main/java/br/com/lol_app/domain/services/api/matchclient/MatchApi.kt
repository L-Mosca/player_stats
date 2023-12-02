package br.com.lol_app.domain.services.api.matchclient

import br.com.lol_app.BuildConfig
import br.com.lol_app.domain.model.matches.MatchDetailResponse
import br.com.lol_app.domain.services.api.ApiConstants
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

interface MatchApi {
    companion object {
        fun newInstance(preferencesHelper: PreferencesHelperContract, url: String): MatchApi {
            val okHttpClient = getOkHttpClient(preferencesHelper)
            val retrofit = getRetrofit(okHttpClient, url)
            return retrofit.create(MatchApi::class.java)
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