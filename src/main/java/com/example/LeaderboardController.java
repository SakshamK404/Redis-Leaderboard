package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/leaderboard")
public class LeaderboardController {

    @Autowired
    private LeaderboardService leaderboardService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitScore(@RequestParam String user, @RequestParam double score) {
        leaderboardService.addScore(user, score);
        return ResponseEntity.ok("Score updated for " + user);
    }

    @GetMapping("/top")
    public Set<ZSetOperations.TypedTuple<String>> getLeaderboard() {
        return leaderboardService.getTopPlayers();
    }
}