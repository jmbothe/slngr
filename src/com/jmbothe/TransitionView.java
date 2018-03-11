package com.jmbothe;

import processing.core.PApplet;

public class TransitionView {
    protected Timer timer;

    protected PApplet p;

    TransitionView(PApplet p) {
        timer = new Timer(3, 100, "Countdown: ", 0.4f, 0.6f, p);
        this.p = p;
    }

    public String makeTimerPrompt () {
        String currentPlayer = Game.get(p).currentPlayer.name().equals("PLAYER_1") ? "Player 1" : "Player 2";
        return currentPlayer + " Ready?";
    }
    public String makePostRoundText() {
        String currentPlayer = Game.get(p).currentPlayer.name().equals("PLAYER_1") ? "Player 1" : "Player 2";
        return "Nice Round " + currentPlayer + "! " + "Your score was " + Game.get(p).players.get(Game.get(p).currentPlayer).score + " points!";

    }

    public void drawPreRound() {
        if (p.frameCount % 60 == 0) {
            timer.countDown();
        }
        p.fill(0);

        p.textAlign(p.CENTER);

        p.textSize(100);
        p.text(makeTimerPrompt(), 0, (float) (p.height * 0.3), p.width, (float) (p.height * 0.3));
        timer.draw();
    }

    public void drawPostRound() {
        if (p.frameCount % 60 == 0) {
            timer.countDown();
        }
        p.fill(0);

        p.textAlign(p.CENTER);

        p.textSize(100);
        p.text(makePostRoundText(), 0, (float) (p.height * 0.3), p.width, (float) (p.height * 0.3));
    }

    public void drawPostMatch() {

    }
}