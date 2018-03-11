package com.jmbothe;

import processing.core.PApplet;

public class Player implements Comparable {
    private int score;
    private int highScore;
    private int wins;

    PApplet p;

    Player(PApplet p) {
        score = 0;
        highScore = 0;
    }

    public void addPoints(int points) {
        score += points;
    }

    public void setHighScore() {
        if (score > highScore) highScore = score;
    }

    public void resetScore() {
        score = 0;
    }

    public void addWin() {
        wins++;
    }

    public int getWins() {
        return wins;
    }

    public int compareTo(Object compare) {
        return ((((Player) compare).wins - this.wins) * 10) + (((Player) compare).highScore - this.highScore);
    }

    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }
}
