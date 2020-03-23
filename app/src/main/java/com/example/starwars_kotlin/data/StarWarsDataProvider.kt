package com.example.starwars_kotlin.data.dta
import com.example.starwars_kotlin.data.api.Api
import com.example.starwars_kotlin.data.api.StarWarsApiC
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object StarWarsDataProvider {

    class StarWarsDataProvider {
        init{

            val loggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .callTimeout(10, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(StarWarsApiC::class.java)
        }

        suspend fun providedPeople() = retrofit
            .getPeopleListAscync().people

        suspend fun providedSinglePeople(peopleId: String) = retrofit
            .getPlanetListAscync(peopleId)
    }
}