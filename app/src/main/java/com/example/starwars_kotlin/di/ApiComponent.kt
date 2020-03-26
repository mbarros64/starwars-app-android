package com.example.starwars_kotlin.di

import com.example.starwars_kotlin.service.NetworkService
import com.example.starwars_kotlin.view.MainActivity
import com.example.starwars_kotlin.viewmodel.PeopleViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(networkService: NetworkService)

    fun inject(peopleViewModel: PeopleViewModel)

    fun inject(mainActivity: MainActivity)
}