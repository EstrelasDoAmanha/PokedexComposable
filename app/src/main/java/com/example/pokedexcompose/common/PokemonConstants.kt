package com.example.pokedexcompose.common

internal fun getPokemonHomeSpriteUrl(id: Int = 0): String  {
    return "https://raw.githubusercontent.com/PokeAPI/" +
        "sprites/master/sprites/pokemon/other/home/$id.png"
}
