package com.example;

public class PlayerRank {

    private int rank;
    private String player;
    private Double score;

    public PlayerRank(int rank, String player, Double score) {
        this.rank = rank;
        this.player = player;
        this.score = score;
    }

    public int getRank() { return rank; }
    public String getPlayer() { return player; }
    public Double getScore() { return score; }
}