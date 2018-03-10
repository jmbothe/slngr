package com.jmbothe;

import processing.core.PApplet;

public class StartRoundCard {
    protected String currentPlayer;
    protected String prompt;
    protected int count;

    protected PApplet p;

    StartRoundCard(PApplet p) {
        currentPlayer = Game.get(p).currentPlayer.name().equals("PLAYER_1") ? "Player 1" : "Player 2";
        prompt = "Player " + currentPlayer + " Ready?";
        this.count = 3;
        this.p = p;
    }

    public void decrementCount() {
        count--;
    }

    public void resetCount() {
        count = 3;
    }

    public void draw() {
        if (p.frameCount % 60 == 0) {
            decrementCount();
        }

        p.fill(0);

        p.textAlign(p.CENTER);

        p.textSize(100);
        p.text(prompt, 0, (float) (p.height * 0.3), p.width, (float) (p.height * 0.3));
        p.text("Countdown: " + count, 0, (float) (p.height * 0.6), p.width, (float) (p.height * 0.6));
    }
}
