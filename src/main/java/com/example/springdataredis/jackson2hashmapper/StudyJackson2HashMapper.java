package com.example.springdataredis.jackson2hashmapper;

import com.example.springdataredis.hashMappers.Person;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Jackson2HashMapper {

    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, byte[], byte[]> hashOperations;
    HashMapper<Object, byte[], byte[]> mapper = new ObjectHashMapper();
    


    public Jackson2HashMapper(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        Jackson2HashMapper mapper = new Jackson2HashMapper(redisTemplate);

        this.hashOperations = redisTemplate.opsForHash();
    }

    public void writeHash(String key, com.example.springdataredis.hashMappers.Person person) {
        // Convert an object to a map that can be used with Redis hashes.
        Map<byte[], byte[]> mappedHash = mapper.toHash(person);
        hashOperations.putAll(key, mappedHash);
    }

    public com.example.springdataredis.hashMappers.Person loadHash(String key) {
        Map<byte[], byte[]> loadedHash = hashOperations.entries(key);
        return (Person) mapper.fromHash(loadedHash);
    }
}