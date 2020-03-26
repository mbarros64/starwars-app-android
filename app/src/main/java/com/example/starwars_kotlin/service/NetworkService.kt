package com.example.starwars_kotlin.service

import com.example.starwars_kotlin.api_endpoint.StarWarsApi
import com.example.starwars_kotlin.di.DaggerApiComponent
import com.example.starwars_kotlin.model.PeopleResult
import io.reactivex.Single
import javax.inject.Inject


class NetworkService {

    @Inject
    lateinit var starWarsApi: StarWarsApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun fetchPeople(): Single<PeopleResult> {
        return starWarsApi.getPeople()
    }

    companion object {
        val BASE_URL = "https://swapi.co/"
    }
}