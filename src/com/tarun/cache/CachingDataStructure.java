package com.tarun.cache;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CachingDataStructure<K, V> {
    private final int maxSize;
    private final Map<K, Cache<K, V>> map;
    private final PriorityQueue<Cache<K, V>> pq;

    public CachingDataStructure(int capacity) {
        this.maxSize = capacity;
        map = new HashMap<>();
        pq = new PriorityQueue<>(Comparator.comparing(Cache::getExpirationTime));
    }

    public void put(K key, V value, long ttl) {
        Cache<K, V> cache = new Cache<>(key, value, System.currentTimeMillis() + ttl);
        if (map.size() > maxSize) {
            evict();
            if (map.size() >= maxSize) {
                evictOldest();
            }
        }
        if (!map.containsKey(key)) {
            map.put(key, cache);
            pq.add(cache);
        } else {
            Cache<K, V> data = map.get(key);
            if (data.getExpirationTime() < cache.getExpirationTime()) {
                pq.remove(cache);
                map.remove(key);
                pq.add(cache);
                map.put(key, cache);
            }
        }
    }

    public V get(K key) {
        Cache<K, V> cache = map.get(key);
        if (cache != null && !isExpired(cache)) {
            return cache.getValue();
        } else {
            map.remove(key);
            pq.remove(cache);
            return null;
        }
    }

    public boolean isExpired(Cache<K, V> cache) {
        return cache.getExpirationTime() < System.currentTimeMillis();
    }

    public void evict() {
        while (!pq.isEmpty() && isExpired(pq.peek())) {
            Cache<K, V> cache = pq.poll();
            map.remove(cache.getKey());
        }
    }

    public void evictOldest() {
        Cache<K, V> cache = pq.poll();
        map.remove(cache.getKey());
    }

    private static class Cache<K, V> {
        private final K key;
        private final V value;
        private final long expirationTime;

        private Cache(K key, V value, long expirationTime) {
            this.key = key;
            this.value = value;
            this.expirationTime = expirationTime;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public long getExpirationTime() {
            return expirationTime;
        }
    }

    public static void main(String []args) {
        CachingDataStructure<Integer, String> cacheDS = new CachingDataStructure<>(2);
        cacheDS.put(10, "Tarun", 5000);
        cacheDS.put(11, "Barun", 10000);
        cacheDS.put(12, "annu", 10000);
        cacheDS.put(11, "BarunKumar", 100);

        System.out.println(cacheDS.get(10));
        System.out.println(cacheDS.get(11));
        System.out.println(cacheDS.get(12));

    }
}
