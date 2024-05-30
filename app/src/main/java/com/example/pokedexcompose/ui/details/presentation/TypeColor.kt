package com.example.pokedexcompose.ui.details.presentation

import androidx.compose.ui.graphics.Color
import com.example.pokedexcompose.ui.theme.Bug
import com.example.pokedexcompose.ui.theme.Dark
import com.example.pokedexcompose.ui.theme.Dragon
import com.example.pokedexcompose.ui.theme.Electric
import com.example.pokedexcompose.ui.theme.Fairy
import com.example.pokedexcompose.ui.theme.Fighting
import com.example.pokedexcompose.ui.theme.Fire
import com.example.pokedexcompose.ui.theme.Fly
import com.example.pokedexcompose.ui.theme.Ghost
import com.example.pokedexcompose.ui.theme.Grass
import com.example.pokedexcompose.ui.theme.Ground
import com.example.pokedexcompose.ui.theme.Ice
import com.example.pokedexcompose.ui.theme.Normal
import com.example.pokedexcompose.ui.theme.Poison
import com.example.pokedexcompose.ui.theme.Psychic
import com.example.pokedexcompose.ui.theme.Rock
import com.example.pokedexcompose.ui.theme.Steel
import com.example.pokedexcompose.ui.theme.Water

enum class TypeColor(val type: String, val color: Color) {
    GRASS("grass", Grass),
    POISON("poison", Poison),
    FIRE("fire", Fire),
    Flying("flying", Fly),
    NORMAL("normal", Normal),
    PSYCHIC("psychic", Psychic),
    BUG("bug", Bug),
    ELECTRIC("electric", Electric),
    GROUND("ground", Ground),
    DARK("dark", Dark),
    WATER("water", Water),
    ICE("ice", Ice),
    STEEL("steel", Steel),
    ROCK("rock", Rock),
    DRAGON("dragon", Dragon),
    GHOST("ghost", Ghost),
    FAIRY("fairy", Fairy),
    FIGHTING("fighting", Fighting);

    companion object {
        fun parse(typeName: String): TypeColor {
            return entries.find {
                it.type == typeName.lowercase()
            } ?: NORMAL
        }
    }
}
