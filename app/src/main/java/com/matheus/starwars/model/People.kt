package com.matheus.starwars.model

import com.squareup.moshi.Json

data class People(
    @field:Json(name = "birth_year") val birth_year: String,
    @field:Json (name = "created") val created: String,
    @field:Json (name = "edited") val edited: String,
    @field:Json (name = "eye_color") val eye_color: String,
    @field:Json (name = "films") val films: List<String>,
    @field:Json (name = "gender") val gender: String,
    @field:Json (name = "hair_color") val hair_color: String,
    @field:Json (name = "height") val height: String,
    @field:Json (name = "homeworld")val homeworld: String,
    @field:Json (name = "mass") val mass: String,
    @field:Json (name = "name") val name: String,
    @field:Json (name = "skin_color") val skin_color: String,
    @field:Json (name = "species") val species: List<String>,
    @field:Json (name = "starships")val starships: List<String>,
    @field:Json (name = "url") val url: String,
    @field:Json (name = "peoples") val peoples: List<String>
)