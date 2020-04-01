package com.mbarros64.starwars.service

import com.mbarros64.starwars.api_endpoint.StarWarsApi
import com.mbarros64.starwars.di.DaggerApiComponent
import com.mbarros64.starwars.model.PeopleResult
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