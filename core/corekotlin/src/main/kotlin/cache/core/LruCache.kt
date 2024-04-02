package cache.core

import cache.source.CacheSource
import cache.source.LruCacheSource

abstract class LruCache<K, V>(
    cacheSource: CacheSource<K,V> = LruCacheSource()
):BaseCache<K,V>(cacheSource)