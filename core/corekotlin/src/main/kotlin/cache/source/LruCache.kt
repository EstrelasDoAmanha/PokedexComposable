package cache.source

import cache.core.BaseCache

abstract class LruCache<K:Any, V>(
    cacheSource: CacheSource<K, V> = LruCacheSource()
):BaseCache<K,V>(cacheSource)