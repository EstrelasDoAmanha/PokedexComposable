package com.example.pokedexcompose.domain.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil.network.HttpException
import com.example.pokedexcompose.data.database.PokemonDao
import com.example.pokedexcompose.data.database.PokemonEntity
import com.example.pokedexcompose.data.datasource.PokemonDataSource
import com.example.pokedexcompose.data.mappers.PokemonListByFilterDtoToDomain
import com.example.pokedexcompose.data.model.PokemonListDto
import com.example.pokedexcompose.domain.mapper.PokemonListDtoToDomain
import com.example.pokedexcompose.domain.model.ResultListDomain
import io.ktor.utils.io.errors.IOException

internal class PokemonListPagingSource(
    private val daoPokemon: PokemonDao,
    private val remoteDataSource: PokemonDataSource,
    private val mapper: PokemonListDtoToDomain,
    private val listPokemonByFilterToDomain: PokemonListByFilterDtoToDomain
) : PagingSource<Int, ResultListDomain>() {

    private val limit = 20
    var query: String = ""

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultListDomain> {
        return try {
            val offset = params.key ?: 0
            val response = if (query.isBlank()) {
                remoteDataSource.getPokemonList(
                    offset = "$offset",
                    limit = "$limit"
                )
            } else {
                val result = remoteDataSource.getPokemonListWithFilter(
                    query = query
                )
                PokemonListDto(result = listPokemonByFilterToDomain.map(result))
            }
            val nextOffset = offset + limit
            val fromDtoToEntity = response.result.map {
                PokemonEntity(
                    name = it.name,
                    url = it.url,
                    gif = it.urlGif,
                    id = it.id.toInt(),
                    type = query
                )
            }
            daoPokemon.insertPokemons(fromDtoToEntity)
            val fromEntityToDomain = mapper.map(
                daoPokemon.getPokemonListByFilter(
                    offset = offset,
                    limit = limit,
                    search = query
                )
            )
            LoadResult.Page(
                data = fromEntityToDomain.result,
                prevKey = if (offset == 0) null else offset - limit,
                nextKey = if (fromEntityToDomain.result.isEmpty()) null else nextOffset
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    suspend fun resetList() {
       this.invalidate()
    }

    override fun getRefreshKey(state: PagingState<Int, ResultListDomain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.minus(1) ?: anchorPage?.nextKey?.plus(1)
        }
    }
}
