package com.example.pokedexcompose.ui.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexcompose.data.local.results
import com.example.pokedexcompose.data.mappers.PokemonDtotoDomain
import com.example.pokedexcompose.data.mappers.PokemonStatsDtoToDomain
import com.example.pokedexcompose.data.mappers.PokemonTypeDtoToDomain
import com.example.pokedexcompose.data.model.PokemonDto
import com.example.pokedexcompose.domain.model.Pokemon
import com.pokedexcompose.network.client.KtorHttpClient
import com.pokedexcompose.network.dsl.getRequest
import com.pokedexcompose.network.dsl.model.KtorClientDslModel
import com.pokedexcompose.network.dsl.request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ListScreenViewModel(
    private val pokemonClient: KtorHttpClient
) : ViewModel() {

    var uiState = MutableStateFlow<ListUiState>(ListUiState(results))
        private set

    class ListUiState(
        val list: List<Pokemon> = results
    )

    init {
        getPokemon()
    }

    private fun getPokemon() {
        viewModelScope.launch(Dispatchers.IO) {
            val request = pokemonClient.httpClient.request<PokemonDto,Any> {
                url = "${pokemonClient.baseUrl}pokemon/pikachu"
            }
            Log.i("tstPokmeon", request.toString())
            val mapper = PokemonDtotoDomain(
                pokemonStatsDtoToDomain = PokemonStatsDtoToDomain(),
                pokemonTypeDtoToDomain = PokemonTypeDtoToDomain()
            )
            val pokemonInfo = mapper.map(request)
            Log.i("tstPokmeon", pokemonInfo.toString())
        }
    }
}
