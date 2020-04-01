package com.mbarros64.starwars.di

import com.mbarros64.starwars.service.NetworkService
import com.mbarros64.starwars.view.MainActivity
import com.mbarros64.starwars.viewmodel.PeopleViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(networkService: NetworkService)

    fun inject(peopleViewModel: PeopleViewModel)

    fun inject(mainActivity: MainActivity)
}