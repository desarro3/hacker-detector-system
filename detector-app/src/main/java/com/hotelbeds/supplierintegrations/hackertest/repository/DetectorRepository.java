package com.hotelbeds.supplierintegrations.hackertest.repository;

import com.hotelbeds.supplierintegrations.hackertest.exceptions.LineLogNotFoundException;
import com.hotelbeds.supplierintegrations.hackertest.models.LogLine;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.List;
import java.util.Map;

public class DetectorRepository {

    private RedisTemplate<String, LogLine> redisTemplate;

    private HashOperations hashOperations;


    public DetectorRepository(RedisTemplate<String, LogLine> redisTemplate) {
        this.redisTemplate = redisTemplate;

        hashOperations = redisTemplate.opsForHash();
    }

    public List<Long> getById(String id) {
        if (exists(id)) {
            return (List<Long>) hashOperations.get("LINE", id);
        }

        throw new LineLogNotFoundException("Not found: " + id);
    }

    public List<LogLine> findById(String id) {

        return (List<LogLine>) hashOperations.get("LINE", id);
    }

    public void save(String id, long value) {

        hashOperations.put("LINE", id, value);
    }

    public Map<String, LogLine> findAll() {

        return hashOperations.entries("LINE");
    }

    public boolean exists(String id) {

        return (boolean) hashOperations.get("LINE", id);
    }

    public void removeAll() {
        hashOperations.delete("LINE");
    }

    public void removeById(String ip) {
        hashOperations.delete("LINE", ip);
    }
}
