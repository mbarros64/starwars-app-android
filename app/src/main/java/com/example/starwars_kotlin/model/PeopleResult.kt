package com.example.starwars_kotlin.model

data class PeopleResult(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<People>
)