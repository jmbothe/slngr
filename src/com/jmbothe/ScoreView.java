package com.jmbothe;

import processing.core.PApplet;

public class ScoreView {
    PApplet p;
    int score;

    ScoreView(PApplet p) {
        this.p = p;
        this.score = 0;
    }

    public void draw() {
        this.score = Game.get(p).currentPlayer.score;
        p.fill(0);
        p.textSize(36);
        p.text("Score: " + score, p.width * 0.1f, p.height * 0.1f);
    }
}
