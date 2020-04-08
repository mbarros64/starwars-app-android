package com.mbarros64.starwars.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbarros64.starwars.R
import com.mbarros64.starwars.adapter.PeopleAdapter
import com.mbarros64.starwars.di.DaggerApiComponent
import com.mbarros64.starwars.model.People
import com.mbarros64.starwars.viewmodel.PeopleViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

const val KEY_NAME = "name"
open class MainActivity : AppCompatActivity() {

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

    private fun CharactersClickedListener(people: People) {
        val intent = Intent(this, PeopleDetailsActivity::class.java)
        intent.putExtra(KEY_NAME, people.name)
        startActivity(intent)
    }
}