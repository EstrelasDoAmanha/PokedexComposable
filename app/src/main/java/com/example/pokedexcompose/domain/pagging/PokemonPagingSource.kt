package com.example.pokedexcompose.domain.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil.network.HttpException
import com.example.pokedexcompose.data.datasource.PokemonDataSource
import com.example.pokedexcompose.domain.mapper.PokemonListDtoToDomain
import com.example.pokedexcompose.domain.model.ResultListDomain
import io.ktor.utils.io.errors.IOException

internal class PokemonPagingSource(
    private val remoteDataSource: PokemonDataSource,
    private val mapper: PokemonListDtoToDomain
) : PagingSource<Int, ResultListDomain>() {
    private val limit = 20
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultListDomain> {
        return try {
            val offset = params.key ?: 0
            val response = remoteDataSource.getPokemonList("$offset", "$limit")
            val data = mapper.map(response)
            val nextOffset = offset + limit

            LoadResult.Page(
                data = data.result,
                prevKey = if (offset == 0) null else offset - limit,
                nextKey = if (data.result.isEmpty()) null else nextOffset
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResultListDomain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.minus(1) ?: anchorPage?.nextKey?.plus(1)
        }
    }
}
