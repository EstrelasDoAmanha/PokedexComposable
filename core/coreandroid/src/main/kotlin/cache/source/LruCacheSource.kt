package cache.source

import androidx.collection.LruCache
import cache.model.CacheData

private const val DEFAULT_SIZE = 4 * 1024 * 1024
class LruCacheSource<K: Any, V>(cacheSize: Int = DEFAULT_SIZE) : CacheSource<K, V> {

    private val inMemoryCache: LruCache<K, CacheData<out V>> = LruCache(cacheSize)
    override val size: Int
        get() = inMemoryCache.size()

    override fun createCacheData(value: V): CacheData<out V> = CacheData(value)

    override fun clear() {
        inMemoryCache.evictAll()
    }

    override fun set(key: K, value: CacheData<out V>) {
        inMemoryCache.put(key, value)
    }

    override fun get(key: K): CacheData<out V>?  = inMemoryCache.get(key)

    override fun deleteCache(key: K) {
       inMemoryCache.remove(key)
    }
}