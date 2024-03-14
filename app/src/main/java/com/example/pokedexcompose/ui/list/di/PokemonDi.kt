package com.example.pokedexcompose.ui.list.di

import com.example.pokedexcompose.ui.list.ListScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModel = module {
    viewModelOf(::ListScreenViewModel)
}