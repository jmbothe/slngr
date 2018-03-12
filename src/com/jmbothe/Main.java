package com.jmbothe;

import processing.core.PApplet;

public class Main extends PApplet {
    protected enum VIEW { MENU, PLAY, PREROUND, POSTROUND, POSTMATCH };
    private static VIEW currentView;

    private Menu menu;
    private TransitionView transitionView;
    private Round round;
    private static int numPlayers;
    private Game game;

    public static void main(String[] args) {
        if (args.length == 1) {
            try {
                // Only allow between 2 and 5 players.
                int playersArg = Integer.parseInt(args[0]);
                numPlayers = playersArg > 1 && playersArg < 6 ? playersArg : 2;

            }
            catch (NumberFormatException nfe) {
                System.out.println("The first argument must be an integer between 2 and 5.");
                System.exit(1);
            }
        }
        // initialize animation
        PApplet.main("com.jmbothe.Main");
    }

    public void settings() {
        size(displayWidth, displayHeight);

//        menu = new Menu(this);
//        transitionView = new TransitionView(this);

        game = Game.get(this);
        game.initGame(numPlayers);

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
                if (menu == null) menu = new Menu(this);

                boolean mouseOverPlay = mouseX < width * 0.3 && mouseY > height * 0.7;

                if (mousePressed && mouseOverPlay) {
                    menu = null;
                    currentView = VIEW.PREROUND;
                } else {
                    menu.draw(mouseOverPlay);
                }
                break;
            case PREROUND:
                if (transitionView == null) transitionView = new TransitionView(this);

                if (transitionView.getTimer().getCount() < 0) {
                    transitionView.getTimer().reset();
                    transitionView = null;
                    currentView = VIEW.PLAY;
                } else {
                    transitionView.draw(currentView);
                }
                break;
            case POSTROUND:
                if (transitionView == null) transitionView = new TransitionView(this);

                if (transitionView.getTimer().getCount() < 0) {
                    game.switchPlayers();
                    transitionView.getTimer().reset();
                    transitionView = null;
                    currentView = game.getGameOver() ? VIEW.POSTMATCH : VIEW.PREROUND;
                } else {
                    transitionView.draw(currentView);
                }
                break;
            case POSTMATCH:
                if (transitionView == null) transitionView = new TransitionView(this);

                if (transitionView.getTimer().getCount() < 0) {
                    transitionView.getTimer().reset();
                    transitionView = null;
                    game.resetGame();
                    currentView = VIEW.MENU;
                } else {
                    transitionView.draw(currentView);
                }
                break;
            case PLAY:
                if (round == null) round = new Round(this);

                if (round.getTimer().getCount() < 0) {
                    round = null;
                    game.clearTargets();
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
