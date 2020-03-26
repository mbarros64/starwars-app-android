package com.example.starwars_kotlin.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwars_kotlin.R
import com.example.starwars_kotlin.adapter.PeopleAdapter
import com.example.starwars_kotlin.di.DaggerApiComponent
import com.example.starwars_kotlin.model.People
import com.example.starwars_kotlin.viewmodel.PeopleViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var peopleAdapter: PeopleAdapter

    private lateinit var peopleViewModel: PeopleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerApiComponent.create().inject(this)

        peopleViewModel = ViewModelProviders.of(this).get(PeopleViewModel::class.java)

        main_swipe_refresh_layout.setOnRefreshListener {
            main_swipe_refresh_layout.isRefreshing = false
            peopleViewModel.refresh()
        }

        main_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = peopleAdapter
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        observeInProgress()
        observeIsError()
        observePeopleList()
    }

    private fun observePeopleList() {
        val peopleListLD: LiveData<List<People>> = peopleViewModel.peopleListMLD
        peopleListLD.observe(this, Observer { allPeoples ->
            allPeoples.let {
                main_recycler_view.visibility = View.VISIBLE
                peopleAdapter.setUpPeoples(it)
            }
        })
    }

    private fun observeInProgress() {
        val inProgressLD: LiveData<Boolean> = peopleViewModel.inProgressMLD
        inProgressLD.observe(this, Observer { isLoading ->
            isLoading.let {
                if (it) {
                    people_fetch_error.visibility = View.GONE
                    main_recycler_view.visibility = View.GONE
                    people_fetch_progress.visibility = View.VISIBLE
                } else {
                    people_fetch_progress.visibility = View.GONE
                }
            }
        })
    }

    private fun observeIsError() {
        val isErrorLD: LiveData<Boolean> = peopleViewModel.isErrorMLD
        isErrorLD.observe(this, Observer { isError ->
            isError.let { people_fetch_error.visibility = if (it) View.VISIBLE else View.GONE }
        })
    }
}