package br.com.lol_app.domain.services.api

import br.com.lol_app.BuildConfig
import br.com.lol_app.domain.services.preferences.PreferencesHelperContract
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
}