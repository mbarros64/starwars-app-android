package com.matheus.starwars.model

data class PeopleResult(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<People>
)