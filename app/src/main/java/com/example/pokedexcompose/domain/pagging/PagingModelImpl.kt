package com.example.pokedexcompose.domain.pagging

import android.util.Log

class PagingModelImpl(override var string: String = ""):PagingModel {
    open override fun updateFilter(update:Boolean) {
        Log.d("TAG", "updateFilter: ")
    }
}