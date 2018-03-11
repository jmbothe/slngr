package com.jmbothe;

import processing.core.PApplet;

public class TransitionView {
    protected Timer timer;

    protected PApplet p;

    TransitionView(PApplet p) {
        timer = new Timer(5, 100, "Countdown: ", 0.5f, p);
        this.p = p;
    }

    private String makePreRoundText () {
        String currentPlayer = "Player " + (Game.get(p).players.indexOf(Game.get(p).getCurrentPlayer()) + 1);
        return currentPlayer + " Ready?";
    }
    private String makePostRoundText() {
        String currentPlayer = "Player " + (Game.get(p).players.indexOf(Game.get(p).getCurrentPlayer()) + 1);
        return "Nice Round " + currentPlayer + "!\n " + "Your score was " + Game.get(p).getCurrentPlayer().getScore() + " points!";
    }

    private String makePostMatchText() {
        if (Game.get(p).getTie()) {
            String players = "";
            for (Player player : Game.get(p).tiedPlayers) {
                players += "Player " + (Game.get(p).players.indexOf(player) + 1) + "\n";
            }
            return "Its a tie between\n" + players + "\nwith a score of " + Game.get(p).getWinnerScore();
        }
        return "Player " + (Game.get(p).players.indexOf(Game.get(p).getWinner()) + 1) +
                " wins\nwith a score of " + Game.get(p).getWinnerScore() + " points!";
    }

    public void draw(Main.VIEW currentView) {
        String text = currentView == Main.VIEW.POSTROUND
                ? makePostRoundText()
                : currentView == Main.VIEW.PREROUND
                ? makePreRoundText()
                : makePostMatchText();

        if (p.frameCount % 30 == 0) timer.countDown();

        p.fill(0);

        p.textAlign(p.CENTER);

        p.textSize(100);
        p.text(text, 0, (float) (p.height * 0.3), p.width, (float) (p.height * 0.3));
        if (currentView == Main.VIEW.PREROUND) timer.draw();
    }
}
