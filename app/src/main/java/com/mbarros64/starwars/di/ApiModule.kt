package com.mbarros64.starwars.di

import androidx.lifecycle.MutableLiveData
import com.mbarros64.starwars.adapter.PeopleAdapter
import com.mbarros64.starwars.apiendpoint.StarWarsApi
import com.mbarros64.starwars.model.People
import com.mbarros64.starwars.service.NetworkService
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


@Module
class ApiModule {

    @Provides
    fun provideStarWarsApi() = StarWarsApi.instance

    @Provides
    fun providePeopleList(): ArrayList<People> {
        return ArrayList()
    }

    @Provides
    fun providePeopleAdapter(people: ArrayList<People>): PeopleAdapter {
        return PeopleAdapter(people)
    }

    @Provides
    fun provideNetworkService(): NetworkService {
        return NetworkService()
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun provideLiveDataListOfPeoples(): MutableLiveData<List<People>> {
        return MutableLiveData()
    }

    @Provides
    fun provideLiveDataBoolean(): MutableLiveData<Boolean> {
        return MutableLiveData()
    }
}
