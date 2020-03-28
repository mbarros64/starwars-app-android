package com.matheus.starwars.api_endpoint
import com.matheus.starwars.model.PeopleResult
import io.reactivex.Single
import retrofit2.http.GET

interface StarWarsApi {
    @GET("api/people")
    fun getPeople(): Single<PeopleResult>
}