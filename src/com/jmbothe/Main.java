package com.jmbothe;

import processing.core.PApplet;

public class Main extends PApplet {
    private Menu menu;
    private Slingshot slingshot;
    private Projectile projectile = null;
    private Timer timer;
    private StartRoundCard startRoundCard;

    protected enum VIEW { MENU, PLAY, STARTROUND, ENDROUND, ENDMATCH };
    protected static VIEW currentView;

    public static void main(String[] args) {
        currentView = VIEW.MENU;
        PApplet.main("com.jmbothe.Main");
    }

    public void settings() {
        size(displayWidth, displayHeight);
        menu = new Menu(this);
        slingshot = new Slingshot(this);
        timer = new Timer(this);
    }

    public void setup() {
        background(102);
    }

    public void draw() {
        background(102);

        if (currentView == VIEW.PLAY) {
            // Add new Target every 60 frames (i.e., every second)
            if (frameCount % 60 == 0) {
                Game.get(this).addTarget();
                timer.countDown();
            }

            if (timer.count < 0) {
                currentView = Game.get(this).gameOver ? VIEW.ENDMATCH : VIEW.ENDROUND;
            }

            //Draw slingshot and projectile
            if (!mousePressed) slingshot.rebound();
            slingshot.draw();
            if (projectile != null) projectile.draw();

            // Update position, draw, and remove Targets as necessary
            Game.get(this).manageTargets(projectile);

            timer.draw();

        } else if (currentView == VIEW.MENU) {
            if (mousePressed && mouseX > width * 0.3 && mouseX < width * 0.6 && mouseY > height * 0.7) {
                startRoundCard = new StartRoundCard(this);
                currentView = VIEW.STARTROUND;
            } else {
                menu.draw();
            }
        } else if (currentView == VIEW.STARTROUND) {
            startRoundCard.draw();
            if (startRoundCard.count < 0) {
                currentView = VIEW.PLAY;
            }
        } else if (currentView == VIEW.ENDROUND) {

        } else if (currentView == VIEW.ENDMATCH) {

        }

    }

    public void mouseReleased() {
        float xVel = (slingshot.x - slingshot.controlX) / 10;
        float yVel = (slingshot.restY - slingshot.controlY) / 10;

        projectile = new Projectile(
                slingshot.controlX + (slingshot.x - slingshot.controlX),
                slingshot.controlY + (slingshot.restY - slingshot.controlY),
                xVel,
                yVel,
                this
        );
    }

}
