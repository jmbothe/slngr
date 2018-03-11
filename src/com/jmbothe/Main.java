package com.jmbothe;

import processing.core.PApplet;

public class Main extends PApplet {
    protected enum VIEW { MENU, PLAY, PREROUND, POSTROUND, POSTMATCH };
    private static VIEW currentView;

    private Menu menu;
    private TransitionView transitionView;
    private Round round;
    private static int numPlayers;

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
        frameRate(30);
        background(102);
    }

    public void draw() {
        background(102);

        switch (currentView) {
            case MENU:
                boolean mouseOverPlay = mouseX < width * 0.3 && mouseY > height * 0.7;

                if (mousePressed && mouseOverPlay) {
                    currentView = VIEW.PREROUND;
                } else {
                    menu.draw(mouseOverPlay);
                }
                break;
            case PREROUND:
                if (transitionView.timer.getCount() < 0) {
                    transitionView.timer.reset();
                    currentView = VIEW.PLAY;
                } else {
                    transitionView.draw(currentView);
                }
                break;
            case POSTROUND:
                if (transitionView.timer.getCount() < 0) {
                    Game.get(this).switchPlayers();
                    transitionView.timer.reset();
                    currentView = Game.get(this).getGameOver() ? VIEW.POSTMATCH : VIEW.PREROUND;
                } else {
                    transitionView.draw(currentView);
                }
                break;
            case POSTMATCH:
                if (transitionView.timer.getCount() < 0) {
                    transitionView.timer.reset();
                    Game.get(this).resetGame();
                    currentView = VIEW.MENU;
                } else {
                    transitionView.draw(currentView);
                }
                break;
            case PLAY:
                if (round == null) round = new Round(this);

                if (round.getTimer().getCount() < 0) {
                    round = null;
                    Game.get(this).clearTargets();
                    currentView = VIEW.POSTROUND;
                } else {
                    round.draw();
                }
                break;
        }

    }

    public void mouseReleased() {
        if (currentView == VIEW.PLAY) {
            round.mouseReleased();
        }
    }
}
