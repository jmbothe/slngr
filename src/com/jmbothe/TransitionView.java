package com.jmbothe;

import processing.core.PApplet;

public class TransitionView {
    private Timer timer;
    private Game game;
    private PApplet p;
    private String preRoundText;
    private String postRoundText;
    private String postMatchText;

    TransitionView(PApplet p) {
        timer = new Timer(5, 100, "Countdown: ", 0.5f, p);
        game = Game.get(p);
        this.p = p;

        String currentPlayer = "Player " + (game.players.indexOf(game.getCurrentPlayer()) + 1);
        preRoundText = currentPlayer + " Ready?";
        postRoundText = "Nice Round " + currentPlayer + "!\n " + "Your score was " + game.getCurrentPlayer().getScore() + " points!";
        if (game.getTie()) {
            String players = "";
            for (Player player : game.tiedPlayers) {
                players += " - Player " + (game.players.indexOf(player) + 1);
            }
            postMatchText = "Its a tie between\n" + players.substring(3) + "\nwith a score of " + game.getWinnerScore();
        } else {
            postMatchText = "Player " + (game.players.indexOf(game.getWinner()) + 1) +
                    " wins\nwith a score of " + game.getWinnerScore() + " points!";
        }
    }

    public Timer getTimer() {
        return timer;
    }

    public void draw(Main.VIEW currentView) {
        String text = currentView == Main.VIEW.POSTROUND
                ? postRoundText
                : currentView == Main.VIEW.PREROUND
                ? preRoundText
                : postMatchText;

        if (p.frameCount % 30 == 0) timer.countDown();

        p.fill(0);

        p.textAlign(p.CENTER);

        p.textSize(100);
        p.text(text, 0, (float) (p.height * 0.3), p.width, (float) (p.height));
        if (currentView == Main.VIEW.PREROUND) timer.draw();
    }
}
