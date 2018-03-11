package com.jmbothe;

import processing.core.PApplet;

public class Player implements Comparable {
    protected int score;
    protected int highScore;

    PApplet p;

    Player(PApplet p) {
        score = 0;
        highScore = 0;
    }

    public void addPoints(int points) {
        score += points;
    }

    public void setHighScore() {
        if (score > highScore) {
            highScore = score;
        }
    }

    public void resetScore() {
        score = 0;
    }

    public int compareTo(Object compare) {
        return ((Player) compare).highScore - this.highScore;
    }
}
