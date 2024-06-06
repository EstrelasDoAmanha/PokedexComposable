package cache.core

import cache.Cache
import cache.model.CacheData
import cache.policy.CachePolicy
import cache.source.CacheSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class BaseCache<K, V>(
    private val cacheSource: CacheSource<K, V>
) : Cache<K, V>, CoroutineScope by CoroutineScope(Dispatchers.IO) {
    override val cachePolicies: List<CachePolicy<V>> = emptyList()
    override val size: Int
        get() = cacheSource.size

    override fun clear() {
        cacheSource.clear()
    }

    override fun delete(key: K) {
        cacheSource.deleteCache(key)
    }

    override fun get(key: K): CacheData<out V>? = cacheSource[key]

    override fun set(key: K, value: V) {
        cacheSource[key] = cacheSource.createCacheData(value)
    }

    override fun getWithPolicies(key: K): V? {
        val cacheData = get(key)
        return if (cacheData != null && isValidCache(cacheData)) {
            cacheData.data
        } else {
            null
        }
    }

    private fun isValidCache(result: CacheData<out V>?): Boolean =
        cachePolicies.isEmpty() || cachePolicies.all { it.isNotExperied(result) }
}
