package com.jmbothe;

import processing.core.PApplet;

public class Menu {
    private String description;
    private Game game;
    private String playerRanking;
    private PApplet p;

    Menu(PApplet p) {
        game = Game.get(p);
        this.p = p;

        playerRanking = "";
        for (Player player : game.playersByRank) {
            playerRanking += "Player " + (game.players.indexOf(player) + 1) + "    wins: " + player.getWins() + ",    high score: "
                    + player.getHighScore() + "\n";
        }
        description = "Each player has 30 seconds to use the slingshot to pop as many balloons as possible. The player with the highest score wins.";
    }

    public void draw(boolean mouseOverPlay) {
        p.fill(0);

        p.textAlign(p.CENTER);

        p.textSize(300);
        p.text("slngr", p.width * 0.5f, p.height * 0.25f);

        p.textSize(50);
        p.text(description, p.width * 0.01f, p.height * 0.4f, p.width * 0.5f, p.height * 0.5f);

        p.textSize(60);
        p.text("Player Rankings:", p.width * 0.75f, p.height * 0.45f);

        p.textSize(40);
        p.text(playerRanking, p.width * 0.75f, p.height * 0.55f);

        if (mouseOverPlay) p.fill(255, 100, 100);
        p.textSize(148);
        p.text("play!", p.width * 0.2f, p.height * 0.8f);
    }

}
