package com.example.starwars_kotlin.data.dta

import com.google.gson.annotations.SerializedName

data class  StarWarsPeople( val name:String,
                            @SerializedName("birth_year") val birthYear:String,
                            @SerializedName("eye_color") val eyeColor:String,
                            val gender:String,
                            @SerializedName("hair_color") val hairColor: String,
                            val height: String,
                            val mass: String,
                            @SerializedName("skin_color") val skinColor: String,
                            val homeworld: String,
                            @SerializedName("films") val filmsUrls: ArrayList<String>,
                            @SerializedName("species") val speciesUrls: ArrayList<String>,
                            @SerializedName("starships") val starshipsUrls: ArrayList<String>,
                            @SerializedName("vehicles ") val vehiclesUrls: ArrayList<String>,
                            val url: String,
                            @SerializedName("created") val creationDate: String,
                            @SerializedName("edited") val editedDate: String)

