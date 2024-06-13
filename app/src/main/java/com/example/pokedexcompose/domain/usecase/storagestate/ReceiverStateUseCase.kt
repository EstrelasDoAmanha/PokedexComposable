package com.example.pokedexcompose.domain.usecase.storagestate

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

internal interface ReceiverStateUseCase {
   suspend fun invoke(): Flow<Preferences>
}
