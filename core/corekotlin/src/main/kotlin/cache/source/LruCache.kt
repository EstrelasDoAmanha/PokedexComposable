package cache.source

import cache.core.BaseCache

abstract class LruCache<K : Any, V>(
    cacheSource: CacheSource<K, V>
) : BaseCache<K, V>(cacheSource)
