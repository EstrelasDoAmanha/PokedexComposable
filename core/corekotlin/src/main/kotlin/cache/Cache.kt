package cache

import cache.model.CacheData
import cache.policy.CachePolicy

interface Cache<K, V> {
    val cachePolicies:List<CachePolicy<V>>

    val size:Int

    fun clear()
    fun delete(key:K)
    fun set(key:K, value: V)
    fun get(key:K): CacheData<out V>?
    fun getWithPolicies(key: K): V?

}