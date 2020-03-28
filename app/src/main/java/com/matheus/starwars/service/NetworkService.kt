package com.matheus.starwars.service

import com.matheus.starwars.api_endpoint.StarWarsApi
import com.matheus.starwars.di.DaggerApiComponent
import com.matheus.starwars.model.PeopleResult
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