package com.matheus.starwars.di

import com.matheus.starwars.service.NetworkService
import com.matheus.starwars.view.MainActivity
import com.matheus.starwars.viewmodel.PeopleViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(networkService: NetworkService)

    fun inject(peopleViewModel: PeopleViewModel)

    fun inject(mainActivity: MainActivity)
}