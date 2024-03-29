package com.example.pokedexcompose.domain.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil.network.HttpException
import com.example.pokedexcompose.data.datasource.PokemonDataSource
import com.example.pokedexcompose.domain.mapper.MapperPokemonDomainImpl
import com.example.pokedexcompose.domain.model.ResultListDomain
import io.ktor.utils.io.errors.IOException

internal class PokemonPagingSource(
    private val remoteDataSource: PokemonDataSource,
    private val mapper: MapperPokemonDomainImpl
) : PagingSource<Int, ResultListDomain>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultListDomain> {

        return try {
            val movies = remoteDataSource.getPokemonList()
            LoadResult.Page(
                data = mapper.map(movies).result,
                prevKey = params.key,
                nextKey = params.key?.plus(1) ?: STARTING_PAGE_INDEX.plus(1)
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResultListDomain>): Int? {
        return state.anchorPosition
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

}