package com.jmbothe;

import processing.core.PApplet;

public class Menu {
    private String description;
    private PApplet p;

    Menu(PApplet p) {
        description = "each player has 30 seconds to use the slingshot to pop as many balloons as possible. The player with the highest score wins.";
        this.p = p;
    }

    public String makePlayerRanking() {
        String result = "";
        for (Player player : Game.get(p).playersByRank) {
            result += "Player " + (Game.get(p).players.indexOf(player) + 1) + " high score: " + player.getHighScore() + "\n";
        }
        return result;
    }

    public void draw(boolean mouseOverPlay) {
        p.fill(0);

        p.textAlign(p.CENTER);

        p.textSize(60);
        p.text(description, 0, (float) (p.height * 0.35), p.width, (float) (p.height * 0.5));

        p.textSize(300);
        p.text("slngr", (float) (p.width * 0.5), (float) (p.height * 0.25));

        p.textSize(60);
        p.text("Player Rankings:", (float) (p.width * 0.6), (float) (p.height * 0.6));

        p.textSize(40);
        p.text(makePlayerRanking(), (float) (p.width * 0.6), (float) (p.height * 0.7));

        if (mouseOverPlay) p.fill(255, 100, 100);

        p.textSize(148);
        p.text("play!", (float) (p.width * 0.2), (float) (p.height * 0.8));
    }

}
