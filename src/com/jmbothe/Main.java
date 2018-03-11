package com.jmbothe;

import processing.core.PApplet;

public class Main extends PApplet {
    private Menu menu;
    private TransitionView transitionView;
    private Round round;

    protected enum VIEW { MENU, PLAY, PREROUND, POSTROUND, POSTMATCH };
    protected static VIEW currentView;

    public static void main(String[] args) {
        currentView = VIEW.MENU;
        PApplet.main("com.jmbothe.Main");
    }

    public void settings() {
        size(displayWidth, displayHeight);
        menu = new Menu(this);
        transitionView = new TransitionView(this);
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
            if (mousePressed && mouseX > width * 0.3 && mouseX < width * 0.6 && mouseY > height * 0.7) {
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
                transitionView.timer.reset();
                currentView = Game.get(this).gameOver ? VIEW.POSTMATCH : VIEW.PREROUND;
                Game.get(this).switchPlayers();
            }
        } else if (currentView == VIEW.POSTMATCH) {

        }
    }

    public void mouseReleased() {
        if (currentView == VIEW.PLAY) {
            round.clicked = true;
        }
    }
}
