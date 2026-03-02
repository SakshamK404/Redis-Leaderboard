package com.example;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedisService {

    private final Jedis jedis;

    private static final String LEADERBOARD_KEY = "game:leaderboard";

    public RedisService() {
        this.jedis = new Jedis("localhost", 6379);
    }

    // Add or update player score
    public void addScore(String player, double score) {
        jedis.zadd(LEADERBOARD_KEY, score, player);
    }

    // Get top N players
    public List<PlayerRank> getTopPlayers(int count) {

        // Jedis 5 returns List, not Set
        List<String> players = jedis.zrevrange(LEADERBOARD_KEY, 0, count - 1);

        List<PlayerRank> result = new ArrayList<>();
        int rank = 1;

        for (String player : players) {
            Double score = jedis.zscore(LEADERBOARD_KEY, player);
            result.add(new PlayerRank(rank++, player, score));
        }

        return result;
    }

    // Get player rank
    public PlayerRank getPlayerRank(String player) {

        Long rank = jedis.zrevrank(LEADERBOARD_KEY, player);
        Double score = jedis.zscore(LEADERBOARD_KEY, player);

        if (rank == null || score == null) {
            return null;
        }

        // convert Long to int safely
        return new PlayerRank(rank.intValue() + 1, player, score);
    }
}