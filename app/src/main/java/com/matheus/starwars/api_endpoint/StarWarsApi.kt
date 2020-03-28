package com.matheus.starwars.api_endpoint
import com.matheus.starwars.model.PeopleResult
import com.matheus.starwars.service.NetworkService
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface StarWarsApi {
    @GET("api/people")
    fun getPeople(): Single<PeopleResult>
    companion object {
        val instance: StarWarsApi
            get() =  Retrofit.Builder()
                .baseUrl(NetworkService.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(StarWarsApi::class.java)
    }
}