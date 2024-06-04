package com.example.pokedexcompose.domain.usecase.storagestate

internal interface SaveStateUseCase {
    suspend operator fun invoke(
        position: Pair<Int, Int>
    )
}
