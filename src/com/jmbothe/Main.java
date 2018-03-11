package com.jmbothe;

import processing.core.PApplet;

public class Main extends PApplet {
    private Menu menu;
    private TransitionView transitionView;
    private Round round;
    private static int numPlayers;

    protected enum VIEW { MENU, PLAY, PREROUND, POSTROUND, POSTMATCH };
    protected static VIEW currentView;

    public static void main(String[] args) {
        numPlayers = 2;
        if (args.length == 1) {
            try {
                numPlayers = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException nfe) {
                System.out.println("The first argument must be an integer.");
                System.exit(1);
            }
        }
        PApplet.main("com.jmbothe.Main");
    }

    public void settings() {
        size(displayWidth, displayHeight);
        menu = new Menu(this);
        transitionView = new TransitionView(this);
        Game.get(this).initGame(numPlayers);
        currentView = VIEW.MENU;
    }

    public void setup() {
        background(102);
    }

    public void draw() {
        background(102);

        if (currentView == VIEW.PLAY) {
            if (round == null) {
                round = new Round(this);
            }
            round.draw();
            if (round.timer.count < 0) {
                currentView = VIEW.POSTROUND;
                round = null;
            }
        } else if (currentView == VIEW.MENU) {
            if (mousePressed && mouseX < width * 0.3 && mouseY > height * 0.7) {
                currentView = VIEW.PREROUND;
            } else {
                menu.draw();
            }
        } else if (currentView == VIEW.PREROUND) {
            transitionView.drawPreRound();
            if (transitionView.timer.count < 0) {
                transitionView.timer.reset();
                currentView = VIEW.PLAY;
            }
        } else if (currentView == VIEW.POSTROUND) {
            transitionView.drawPostRound();
            if (transitionView.timer.count < 0) {
                Game.get(this).switchPlayers();
                transitionView.timer.reset();
                currentView = Game.get(this).gameOver ? VIEW.POSTMATCH : VIEW.PREROUND;

            }
        } else if (currentView == VIEW.POSTMATCH) {
            transitionView.drawPostMatch();
            if (transitionView.timer.count < 0) {
                transitionView.timer.reset();
                currentView = VIEW.MENU;
                Game.get(this).resetGame();
            }
        }
    }

    public void mouseReleased() {
        if (currentView == VIEW.PLAY) {
            round.clicked = true;
        }
    }
}
