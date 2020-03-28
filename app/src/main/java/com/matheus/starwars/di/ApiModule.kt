package com.matheus.starwars.di

import androidx.lifecycle.MutableLiveData
import com.matheus.starwars.adapter.PeopleAdapter
import com.matheus.starwars.api_endpoint.StarWarsApi
import com.matheus.starwars.model.People
import com.matheus.starwars.service.NetworkService
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
class ApiModule {

    @Provides
    fun provideStarWarsApi(): StarWarsApi {
        return Retrofit.Builder()
            .baseUrl(NetworkService.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(StarWarsApi::class.java)
    }

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
