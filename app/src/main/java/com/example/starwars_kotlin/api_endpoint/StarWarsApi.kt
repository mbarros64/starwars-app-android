package com.example.starwars_kotlin.api_endpoint
import io.reactivex.Single
import retrofit2.http.GET
import com.example.starwars_kotlin.model.PeopleResult

interface StarWarsApi {
    @GET("api/people")
    fun getPeople(): Single<PeopleResult>
}