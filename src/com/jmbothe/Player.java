package com.jmbothe;

import processing.core.PApplet;

public class Player {
    protected int score;
    protected int highScore;
    protected int rank;

    PApplet p;

    Player(PApplet p) {
        score = 0;
        highScore = 0;
    }

    public void addPoints(int points) {
        score += points;
    }

    public void setHighScore(int score) {
        if (score > this.score) {
            this.score = score;
        }
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
