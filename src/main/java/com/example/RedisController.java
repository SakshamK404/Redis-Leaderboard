package com.example;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
public class RedisController {

    private final RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    // Add or update player score
    @PostMapping("/add")
    public String addScore(@RequestBody LeaderboardRequest request) {
        redisService.addScore(request.getPlayer(), request.getScore());
        return "Score updated successfully!";
    }

    // Get top N players
    @GetMapping("/top")
    public List<PlayerRank> getTopPlayers(@RequestParam(defaultValue = "3") int count) {
        return redisService.getTopPlayers(count);
    }

    // Get specific player rank
    @GetMapping("/rank")
    public PlayerRank getPlayerRank(@RequestParam String player) {
        return redisService.getPlayerRank(player);
    }
}