package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class LeaderboardService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String LEADERBOARD_KEY = "gaming_leaderboard";

    // 1. Submit Score (Interview ready: Use ZINCRBY to avoid race conditions)
    public void addScore(String username, double score) {
        // ZINCRBY adds to the existing score instead of overwriting it
        redisTemplate.opsForZSet().incrementScore(LEADERBOARD_KEY, username, score);
    }

    // 2. Get Top 10 (Interview ready: Returns scores and names)
    public Set<ZSetOperations.TypedTuple<String>> getTopPlayers() {
        return redisTemplate.opsForZSet().reverseRangeWithScores(LEADERBOARD_KEY, 0, 9);
    }

    // 3. Get Specific User Rank
    public Long getRank(String username) {
        // Returns 0-based rank, so we add 1
        Long rank = redisTemplate.opsForZSet().reverseRank(LEADERBOARD_KEY, username);
        return (rank != null) ? rank + 1 : null;
    }
}