package com.example.starwars_kotlin.data.api

import com.example.starwars_kotlin.data.dta.StarWarsPeople
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsApiC {
    @GET("people/{personId}")
    fun loadPerson(@Path("personId") personId:String) : Deferred<StarWarsPeople>
}