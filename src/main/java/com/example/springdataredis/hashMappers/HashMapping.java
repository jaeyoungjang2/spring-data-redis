package com.example.springdataredis.hashMappers;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HashMapping {

    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, byte[], byte[]> hashOperations;
    HashMapper<Object, byte[], byte[]> mapper = new ObjectHashMapper();

    public HashMapping(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void writeHash(String key, Person person) {
        // Convert an object to a map that can be used with Redis hashes.
        Map<byte[], byte[]> mappedHash = mapper.toHash(person);
        hashOperations.putAll(key, mappedHash);
    }

    public Person loadHash(String key) {
        Map<byte[], byte[]> loadedHash = hashOperations.entries(key);
        return (Person) mapper.fromHash(loadedHash);
    }
}