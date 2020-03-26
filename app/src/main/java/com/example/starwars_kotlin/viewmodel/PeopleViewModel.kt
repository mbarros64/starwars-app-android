package com.example.starwars_kotlin.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starwars_kotlin.di.DaggerApiComponent
import com.example.starwars_kotlin.model.People
import com.example.starwars_kotlin.service.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PeopleViewModel : ViewModel() {

    @Inject
    lateinit var networkService: NetworkService
    @Inject
    lateinit var compositeDisposable: CompositeDisposable
    @Inject
    lateinit var peopleListMLD: MutableLiveData<List<People>>
    @Inject
    lateinit var inProgressMLD: MutableLiveData<Boolean>
    @Inject
    lateinit var isErrorMLD: MutableLiveData<Boolean>

    init {
        DaggerApiComponent.create().inject(this)
        fetchPeople()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun refresh() {
        fetchPeople()
    }

    private fun fetchPeople() {
        compositeDisposable.add(
            networkService.fetchPeople()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it.results }
                .subscribeWith(createPeopleObserver())
        )
    }

    private fun createPeopleObserver(): DisposableSingleObserver<List<People>> {
        return object : DisposableSingleObserver<List<People>>() {

            override fun onSuccess(people: List<People>) {
                inProgressMLD.value = true
                isErrorMLD.value = false
                peopleListMLD.value = people
                inProgressMLD.value = false
            }

            override fun onError(e: Throwable) {
                inProgressMLD.value = true
                isErrorMLD.value = true
                Log.e("onError()", "Error: ${e.message}")
                inProgressMLD.value = false
            }
        }
    }
}
