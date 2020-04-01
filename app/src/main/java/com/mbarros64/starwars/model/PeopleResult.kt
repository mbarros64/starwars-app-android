package com.mbarros64.starwars.model

data class PeopleResult(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<People>
)