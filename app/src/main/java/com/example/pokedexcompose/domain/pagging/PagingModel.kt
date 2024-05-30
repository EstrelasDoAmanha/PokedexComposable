package com.example.pokedexcompose.domain.pagging

interface PagingModel {
    var string:String
    open fun updateFilter(update:Boolean)
}