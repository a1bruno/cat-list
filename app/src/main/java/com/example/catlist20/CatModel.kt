package com.example.catlist20

data class CatModel(
    val id: Int,
    val name: String,
    val origin: String,
    val temperament: String,
    val colors: List<String>,
    val description: String,
    val image: String
)