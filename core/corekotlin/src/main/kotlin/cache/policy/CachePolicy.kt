package cache.policy

import cache.model.CacheData

interface CachePolicy<V> {
    fun isNotExperied(cacheData: CacheData<out V>?): Boolean
}